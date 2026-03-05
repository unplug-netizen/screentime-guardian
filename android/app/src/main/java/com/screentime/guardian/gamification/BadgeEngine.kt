package com.screentime.guardian.gamification

import com.screentime.guardian.domain.AppCategory
import com.screentime.guardian.domain.DailyUsage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Pure-Data-Game-Engine
 * Basierend NUR auf System-Metriken:
 * - totalScreenTime (täglich)
 * - appCategoryUsage (System-Kategorien)
 * - unlockCount (System)
 * - focusSessionDuration (App-intern gemessen)
 */
class BadgeEngine {
    
    private val badgeDefinitions = BadgeDefinitions.ALL_BADGES
    
    /**
     * Prüft alle Badges gegen aktuelle Daten
     */
    fun checkBadges(
        history: List<DailyUsage>,
        focusSessions: List<FocusSession>,
        currentStreaks: Map<StreakType, Int>
    ): List<Badge> {
        return badgeDefinitions.map { badge ->
            val progress = calculateProgress(badge, history, focusSessions, currentStreaks)
            val isUnlocked = progress >= 1.0f
            
            badge.copy(
                progress = progress.coerceIn(0f, 1f),
                isUnlocked = isUnlocked || badge.isUnlocked,
                unlockedAt = if (isUnlocked && badge.unlockedAt == null) 
                    System.currentTimeMillis() else badge.unlockedAt
            )
        }
    }
    
    /**
     * Berechnet Score für Leaderboard
     * Score = (100 - dailyScreenTimeHours) + focusMinutes
     */
    fun calculateScore(dailyUsage: DailyUsage): Int {
        val screenTimeHours = dailyUsage.totalScreenTimeHours
        val focusMinutes = dailyUsage.focusTimeMinutes.toInt()
        
        return ((100 - screenTimeHours.coerceAtMost(100.0)) + focusMinutes).toInt()
    }
    
    /**
     * Anonymisiert User-ID mit SHA-256
     * Output: "User #8A3F" (erste 4 Zeichen des Hashes)
     */
    fun anonymizeUserId(userId: String): String {
        val hash = hashString(userId)
        return "User #${hash.take(4).uppercase()}"
    }
    
    private fun calculateProgress(
        badge: Badge,
        history: List<DailyUsage>,
        focusSessions: List<FocusSession>,
        streaks: Map<StreakType, Int>
    ): Float {
        return when (val criteria = badge.criteria) {
            is BadgeCriteria.DailyFocusGoal -> {
                val daysWithGoal = history.count { it.focusTimeMinutes >= criteria.hours * 60 }
                daysWithGoal.toFloat() / criteria.daysNeeded.coerceAtLeast(1)
            }
            
            is BadgeCriteria.StreakDays -> {
                val streakType = when (criteria.streakType) {
                    StreakType.DAILY_FOCUS -> streaks[StreakType.DAILY_FOCUS] ?: 0
                    StreakType.DISTRACTION_FREE -> streaks[StreakType.DISTRACTION_FREE] ?: 0
                    StreakType.EARLY_START -> streaks[StreakType.EARLY_START] ?: 0
                }
                streakType.toFloat() / criteria.days
            }
            
            is BadgeCriteria.TotalFocusTime -> {
                val totalMinutes = history.sumOf { it.focusTimeMinutes }.toInt()
                totalMinutes.toFloat() / (criteria.hours * 60)
            }
            
            is BadgeCriteria.LowDistractionStreak -> {
                // Zähle Tage mit < 10% Ablenkung
                val lowDistractionDays = history.count { day ->
                    val socialTime = day.categoryUsage[AppCategory.SOCIAL] ?: 0
                    val gamesTime = day.categoryUsage[AppCategory.GAMES] ?: 0
                    val totalDistraction = socialTime + gamesTime
                    val distractionPercent = if (day.totalScreenTimeSeconds > 0) {
                        totalDistraction.toFloat() / day.totalScreenTimeSeconds
                    } else 0f
                    distractionPercent < 0.10f
                }
                lowDistractionDays.toFloat() / criteria.days
            }
            
            is BadgeCriteria.EarlyBird -> {
                // Zähle Sessions vor 12:00
                val earlySessions = focusSessions.count { it.startedAtHour < 12 }
                earlySessions.toFloat() / criteria.sessionsBeforeNoon
            }
            
            is BadgeCriteria.DeepDiver -> {
                // Zähle Sessions über X Minuten
                val deepSessions = focusSessions.count { 
                    it.durationMinutes >= criteria.sessionMinutes 
                }
                (deepSessions > 0).toProgress(if (deepSessions > 0) 1f else 0f)
            }
            
            is BadgeCriteria.LimitMaster -> {
                // Tage wo alle Limits eingehalten wurden
                val daysWithLimitsKept = history.count { it.limitAdherenceRate >= 1.0f }
                daysWithLimitsKept.toFloat() / criteria.days
            }
        }
    }
    
    private fun Boolean.toProgress(value: Float): Float = if (this) value else 0f
    
    private fun hashString(input: String): String {
        val bytes = input.toByteArray()
        val md = java.security.MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.joinToString("") { "%02x".format(it) }
    }
}

// Badge Data Classes

data class Badge(
    val id: String,
    val name: String,
    val description: String,
    val tier: BadgeTier,
    val criteria: BadgeCriteria,
    val iconAsset: String,
    val isUnlocked: Boolean = false,
    val unlockedAt: Long? = null,
    val progress: Float = 0f
)

enum class BadgeTier {
    BRONZE, SILVER, GOLD, PLATINUM, SPECIAL
}

sealed class BadgeCriteria {
    data class DailyFocusGoal(val hours: Int, val daysNeeded: Int) : BadgeCriteria()
    data class StreakDays(val days: Int, val streakType: StreakType) : BadgeCriteria()
    data class TotalFocusTime(val hours: Int) : BadgeCriteria()
    data class LowDistractionStreak(val days: Int) : BadgeCriteria()
    data class EarlyBird(val sessionsBeforeNoon: Int) : BadgeCriteria()
    data class DeepDiver(val sessionMinutes: Int) : BadgeCriteria()
    data class LimitMaster(val days: Int) : BadgeCriteria()
}

data class FocusSession(
    val id: String,
    val durationMinutes: Int,
    val startedAtHour: Int, // 0-23
    val date: String // YYYY-MM-DD
)

enum class StreakType {
    DAILY_FOCUS, DISTRACTION_FREE, EARLY_START
}

// Badge Definitions
object BadgeDefinitions {
    val ALL_BADGES = listOf(
        // BRONZE - Getting Started
        Badge(
            id = "first_focus",
            name = "First Steps",
            description = "Complete your first focus session",
            tier = BadgeTier.BRONZE,
            criteria = BadgeCriteria.DailyFocusGoal(hours = 0, daysNeeded = 1),
            iconAsset = "badge_first_steps"
        ),
        Badge(
            id = "early_bird_bronze",
            name = "Early Bird",
            description = "Complete 3 focus sessions before 12:00 PM",
            tier = BadgeTier.BRONZE,
            criteria = BadgeCriteria.EarlyBird(sessionsBeforeNoon = 3),
            iconAsset = "badge_early_bird"
        ),
        
        // SILVER - Building Habits
        Badge(
            id = "week_warrior",
            name = "Week Warrior",
            description = "7 consecutive days meeting 6+ hour focus goal",
            tier = BadgeTier.SILVER,
            criteria = BadgeCriteria.StreakDays(days = 7, streakType = StreakType.DAILY_FOCUS),
            iconAsset = "badge_week_warrior"
        ),
        Badge(
            id = "deep_diver_silver",
            name = "Deep Diver",
            description = "Sustained single session exceeding 2 hours",
            tier = BadgeTier.SILVER,
            criteria = BadgeCriteria.DeepDiver(sessionMinutes = 120),
            iconAsset = "badge_deep_diver"
        ),
        Badge(
            id = "distraction_denier",
            name = "Distraction Denier",
            description = "Keep distracting app usage under 10% for 7 days",
            tier = BadgeTier.SILVER,
            criteria = BadgeCriteria.LowDistractionStreak(days = 7),
            iconAsset = "badge_distraction_denier"
        ),
        
        // GOLD - Mastery
        Badge(
            id = "month_master",
            name = "Month Master",
            description = "30 consecutive days of 6+ hour focus",
            tier = BadgeTier.GOLD,
            criteria = BadgeCriteria.StreakDays(days = 30, streakType = StreakType.DAILY_FOCUS),
            iconAsset = "badge_month_master"
        ),
        Badge(
            id = "focus_champion",
            name = "Focus Champion",
            description = "Accumulate 100 hours of focus time",
            tier = BadgeTier.GOLD,
            criteria = BadgeCriteria.TotalFocusTime(hours = 100),
            iconAsset = "badge_focus_champion"
        ),
        
        // PLATINUM - Elite
        Badge(
            id = "centurion",
            name = "Centurion",
            description = "100 consecutive days meeting your goals",
            tier = BadgeTier.PLATINUM,
            criteria = BadgeCriteria.StreakDays(days = 100, streakType = StreakType.DAILY_FOCUS),
            iconAsset = "badge_centurion"
        ),
        Badge(
            id = "limit_legend",
            name = "Limit Legend",
            description = "Adhere to all app limits for 30 days",
            tier = BadgeTier.PLATINUM,
            criteria = BadgeCriteria.LimitMaster(days = 30),
            iconAsset = "badge_limit_legend"
        ),
        
        // SPECIAL - Unique Achievements
        Badge(
            id = "marathon",
            name = "Marathon",
            description = "Complete a 4-hour focus session",
            tier = BadgeTier.SPECIAL,
            criteria = BadgeCriteria.DeepDiver(sessionMinutes = 240),
            iconAsset = "badge_marathon"
        ),
        Badge(
            id = "night_owl",
            name = "Night Owl",
            description = "Complete 5 sessions after 10 PM",
            tier = BadgeTier.SPECIAL,
            criteria = BadgeCriteria.EarlyBird(sessionsBeforeNoon = 5), // Modified criteria needed
            iconAsset = "badge_night_owl"
        )
    )
}
