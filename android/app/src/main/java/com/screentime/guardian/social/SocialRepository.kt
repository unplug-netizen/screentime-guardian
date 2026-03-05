package com.screentime.guardian.social

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FieldValue
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.security.MessageDigest
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.UUID

/**
 * Privacy-First Social Layer
 * - Keine Kontakt-Uploads
 * - Nur gehashte User-IDs
 * - Firebase Dynamic Links für Einladungen
 */
class SocialRepository(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) {
    private val challengesCollection = db.collection("challenges")
    private val usersCollection = db.collection("users")
    
    /**
     * Erstellt eine neue Challenge
     */
    suspend fun createChallenge(
        type: ChallengeType,
        durationDays: Int,
        invitedUserHashes: List<String>
    ): Result<Challenge> {
        val currentUser = auth.currentUser ?: return Result.failure(Exception("Not authenticated"))
        val userHash = anonymizeUserId(currentUser.uid)
        
        val challengeId = UUID.randomUUID().toString()
        val startDate = LocalDate.now()
        val endDate = startDate.plusDays(durationDays.toLong())
        
        val participants = mutableListOf(userHash)
        participants.addAll(invitedUserHashes)
        
        val challenge = Challenge(
            id = challengeId,
            type = type,
            durationDays = durationDays,
            startDate = startDate.toString(),
            endDate = endDate.toString(),
            participants = participants,
            createdBy = userHash,
            status = ChallengeStatus.ACTIVE,
            createdAt = System.currentTimeMillis()
        )
        
        return try {
            challengesCollection.document(challengeId).set(challenge.toMap()).await()
            Result.success(challenge)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Generiert Einladungs-Link (Firebase Dynamic Link Konzept)
     * In Production: Echter Dynamic Link via Firebase API
     */
    fun generateInviteLink(challengeId: String): String {
        return "https://screentimeguardian.app/invite?challenge=$challengeId"
    }
    
    /**
     * Tritt einer Challenge bei
     */
    suspend fun joinChallenge(challengeId: String): Result<Unit> {
        val currentUser = auth.currentUser ?: return Result.failure(Exception("Not authenticated"))
        val userHash = anonymizeUserId(currentUser.uid)
        
        return try {
            challengesCollection.document(challengeId)
                .update("participants", FieldValue.arrayUnion(userHash))
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Holt alle aktiven Challenges des Users
     */
    suspend fun getActiveChallenges(): List<Challenge> {
        val currentUser = auth.currentUser ?: return emptyList()
        val userHash = anonymizeUserId(currentUser.uid)
        
        return try {
            val snapshot = challengesCollection
                .whereArrayContains("participants", userHash)
                .whereEqualTo("status", ChallengeStatus.ACTIVE.name)
                .get()
                .await()
            
            snapshot.documents.mapNotNull { it.toChallenge() }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    /**
     * Berechnet Challenge-Ergebnisse basierend auf System-Metriken
     */
    suspend fun calculateChallengeResults(challengeId: String): List<ChallengeResult> {
        val challenge = getChallengeById(challengeId) ?: return emptyList()
        
        return challenge.participants.mapNotNull { userHash ->
            val stats = getUserStatsForChallenge(userHash, challenge)
            ChallengeResult(
                userHash = userHash,
                score = calculateScoreForChallenge(stats, challenge.type),
                rank = 0 // Wird später berechnet
            )
        }.sortedByDescending { it.score }
            .mapIndexed { index, result -> result.copy(rank = index + 1) }
    }
    
    /**
     * Anonymisiert User-ID mit SHA-256
     * Format: "User #8A3F" (erste 4 Zeichen)
     */
    fun anonymizeUserId(userId: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(userId.toByteArray())
        val hashString = hash.joinToString("") { "%02x".format(it) }
        return "User #${hashString.take(4).uppercase()}"
    }
    
    /**
     * Holt Leaderboard-Daten (anonymisiert)
     */
    suspend fun getLeaderboard(period: LeaderboardPeriod = LeaderboardPeriod.WEEKLY): List<LeaderboardEntry> {
        return try {
            val snapshot = usersCollection
                .orderBy("score", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .limit(50)
                .get()
                .await()
            
            snapshot.documents.mapIndexed { index, doc ->
                LeaderboardEntry(
                    userHash = doc.getString("userHash") ?: "User #????",
                    score = doc.getLong("score")?.toInt() ?: 0,
                    rank = index + 1,
                    trend = Trend.STABLE // TODO: Implement trend calculation
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    /**
     * Synced aggregierte Stats zu Firebase (nur für Leaderboard)
     */
    suspend fun syncUserStats(aggregatedStats: AggregatedDailyStats) {
        val currentUser = auth.currentUser ?: return
        val userHash = anonymizeUserId(currentUser.uid)
        
        val score = calculateLeaderboardScore(aggregatedStats)
        
        val data = hashMapOf(
            "userHash" to userHash,
            "score" to score,
            "lastSync" to System.currentTimeMillis(),
            "totalScreenTimeMinutes" to aggregatedStats.totalScreenTimeMinutes,
            "focusTimeMinutes" to aggregatedStats.focusTimeMinutes
        )
        
        usersCollection.document(currentUser.uid).set(data).await()
    }
    
    // Private Helpers
    
    private suspend fun getChallengeById(challengeId: String): Challenge? {
        return try {
            challengesCollection.document(challengeId).get().await()
                .toChallenge()
        } catch (e: Exception) {
            null
        }
    }
    
    private suspend fun getUserStatsForChallenge(userHash: String, challenge: Challenge): ChallengeStats {
        // In Production: Aus lokaler DB oder Firestore laden
        return ChallengeStats(
            userHash = userHash,
            totalScreenTimeMinutes = 0,
            focusTimeMinutes = 0,
            limitAdherenceRate = 1.0f
        )
    }
    
    private fun calculateScoreForChallenge(stats: ChallengeStats, type: ChallengeType): Int {
        return when (type) {
            ChallengeType.LEAST_SCREEN_TIME -> 100 - (stats.totalScreenTimeMinutes / 60)
            ChallengeType.MOST_FOCUS_TIME -> stats.focusTimeMinutes
            ChallengeType.LIMIT_ADHERENCE -> (stats.limitAdherenceRate * 100).toInt()
            ChallengeType.STREAK_MAINTAIN -> 0 // TODO: Implement streak tracking
        }
    }
    
    private fun calculateLeaderboardScore(stats: AggregatedDailyStats): Int {
        return (100 - (stats.totalScreenTimeMinutes / 60)) + stats.focusTimeMinutes
    }
}

// Data Classes

data class Challenge(
    val id: String,
    val type: ChallengeType,
    val durationDays: Int,
    val startDate: String,
    val endDate: String,
    val participants: List<String>,
    val createdBy: String,
    val status: ChallengeStatus,
    val createdAt: Long
) {
    fun toMap(): Map<String, Any> {
        return hashMapOf(
            "id" to id,
            "type" to type.name,
            "durationDays" to durationDays,
            "startDate" to startDate,
            "endDate" to endDate,
            "participants" to participants,
            "createdBy" to createdBy,
            "status" to status.name,
            "createdAt" to createdAt
        )
    }
}

data class ChallengeResult(
    val userHash: String,
    val score: Int,
    val rank: Int
)

data class ChallengeStats(
    val userHash: String,
    val totalScreenTimeMinutes: Int,
    val focusTimeMinutes: Int,
    val limitAdherenceRate: Float
)

data class LeaderboardEntry(
    val userHash: String,
    val score: Int,
    val rank: Int,
    val trend: Trend
)

data class AggregatedDailyStats(
    val date: String,
    val totalScreenTimeMinutes: Int,
    val focusTimeMinutes: Int,
    val unlockCount: Int,
    val limitAdherenceRate: Float
)

enum class ChallengeType {
    LEAST_SCREEN_TIME,
    MOST_FOCUS_TIME,
    LIMIT_ADHERENCE,
    STREAK_MAINTAIN
}

enum class ChallengeStatus {
    ACTIVE,
    COMPLETED,
    CANCELLED
}

enum class LeaderboardPeriod {
    DAILY,
    WEEKLY,
    MONTHLY
}

enum class Trend {
    UP, DOWN, STABLE
}

// Extension
private fun com.google.firebase.firestore.DocumentSnapshot.toChallenge(): Challenge? {
    return Challenge(
        id = getString("id") ?: return null,
        type = ChallengeType.valueOf(getString("type") ?: return null),
        durationDays = getLong("durationDays")?.toInt() ?: 0,
        startDate = getString("startDate") ?: "",
        endDate = getString("endDate") ?: "",
        participants = get("participants") as? List<String> ?: emptyList(),
        createdBy = getString("createdBy") ?: "",
        status = ChallengeStatus.valueOf(getString("status") ?: "ACTIVE"),
        createdAt = getLong("createdAt") ?: 0
    )
}
