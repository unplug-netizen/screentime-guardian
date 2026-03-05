package com.screentime.guardian.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.time.temporal.ChronoUnit

/**
 * Local-First Repository mit 30 Tagen Historie
 */
class ScreenTimeRepository(
    private val localDataSource: LocalScreenTimeDataSource,
    private val remoteDataSource: RemoteScreenTimeDataSource? = null
) {
    /**
     * Speichert DailyUsage lokal und trigger Sync
     */
    suspend fun saveDailyUsage(usage: DailyUsage) {
        localDataSource.saveDailyUsage(usage)
        
        // Sync zu Firebase (nur aggregierte Daten)
        remoteDataSource?.syncDailyStats(usage.toSyncData())
    }
    
    /**
     * Holt Historie für die letzten X Tage
     */
    suspend fun getUsageHistory(days: Int = 30): List<DailyUsage> {
        val endDate = LocalDate.now()
        val startDate = endDate.minusDays(days.toLong())
        return localDataSource.fetchUsageHistory(startDate, endDate)
    }
    
    /**
     * Berechnet Streaks basierend auf lokalen Daten
     */
    suspend fun calculateStreak(type: StreakType, goalMinutes: Int): Streak {
        val history = try {
            getUsageHistory(365) // Max für longest streak
        } catch (e: Exception) {
            emptyList()
        }
        
        if (history.isEmpty()) {
            return Streak(currentStreak = 0, longestStreak = 0, lastActiveDate = null, streakType = type)
        }
        
        val sortedDays = history.sortedByDescending { it.date }
        var currentStreak = 0
        var longestStreak = 0
        var tempStreak = 0
        var lastActiveDate: LocalDate? = null
        
        var previousDate: LocalDate? = null
        
        for (day in sortedDays) {
            val metGoal = didMeetStreakGoal(day, type, goalMinutes)
            
            if (metGoal) {
                if (previousDate != null && ChronoUnit.DAYS.between(day.date, previousDate) == 1L) {
                    tempStreak++
                } else {
                    tempStreak = 1
                }
                
                if (currentStreak == 0) {
                    currentStreak = tempStreak
                    lastActiveDate = day.date
                }
                
                longestStreak = maxOf(longestStreak, tempStreak)
                previousDate = day.date
            } else {
                if (currentStreak == 0) {
                    // Streak gebrochen heute
                    break
                }
                tempStreak = 0
            }
        }
        
        return Streak(
            currentStreak = currentStreak,
            longestStreak = longestStreak,
            lastActiveDate = lastActiveDate,
            streakType = type
        )
    }
    
    private fun didMeetStreakGoal(day: DailyUsage, type: StreakType, goalMinutes: Int): Boolean {
        return when (type) {
            StreakType.DAILY_FOCUS -> day.focusTimeSeconds >= goalMinutes * 60
            StreakType.DISTRACTION_FREE -> true // Placeholder - depends on limit adherence
            StreakType.EARLY_START -> day.focusSessionsCompleted > 0
        }
    }
    
    // Extension für Sync
    private fun DailyUsage.toSyncData(): AggregatedDailyStats {
        return AggregatedDailyStats(
            date = date.toString(),
            totalScreenTimeMinutes = (totalScreenTimeSeconds / 60).toInt(),
            focusTimeMinutes = (focusTimeSeconds / 60).toInt(),
            unlockCount = unlockCount,
            limitAdherenceRate = 1.0f // Placeholder
        )
    }
}

// Data Source Interfaces

interface LocalScreenTimeDataSource {
    suspend fun saveDailyUsage(usage: DailyUsage)
    suspend fun fetchUsageHistory(from: LocalDate, to: LocalDate): List<DailyUsage>
    suspend fun fetchDailyUsage(forDate: LocalDate): DailyUsage?
    suspend fun saveLimit(limit: LimitEntity)
    suspend fun fetchActiveLimits(): List<LimitEntity>
    suspend fun deleteLimit(id: String)
}

interface RemoteScreenTimeDataSource {
    suspend fun syncDailyStats(stats: AggregatedDailyStats)
    suspend fun fetchLeaderboard(): List<LeaderboardEntry>
}

// Gamification Models

data class Streak(
    val currentStreak: Int,
    val longestStreak: Int,
    val lastActiveDate: LocalDate?,
    val streakType: StreakType
)

enum class StreakType {
    DAILY_FOCUS,
    DISTRACTION_FREE,
    EARLY_START
}

data class AggregatedDailyStats(
    val date: String, // YYYY-MM-DD
    val totalScreenTimeMinutes: Int,
    val focusTimeMinutes: Int,
    val unlockCount: Int,
    val limitAdherenceRate: Float
)

data class LeaderboardEntry(
    val userHash: String,
    val score: Int,
    val rank: Int,
    val trend: Trend
)

enum class Trend {
    UP, DOWN, STABLE
}
