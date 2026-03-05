// build.gradle.kts dependencies additions needed:
/*
implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
kapt("androidx.room:room-compiler:$roomVersion")
*/

package com.screentime.guardian.domain

import kotlinx.serialization.Serializable
import java.time.LocalDate

/**
 * Domain Models - Serializable for JSON persistence
 */

@Serializable
sealed class BadgeCriteria {
    @Serializable
    data class DailyFocusGoal(val hours: Int, val daysNeeded: Int = 1) : BadgeCriteria()
    
    @Serializable
    data class StreakDays(val days: Int, val streakType: StreakType) : BadgeCriteria()
    
    @Serializable
    data class TotalFocusTime(val hours: Int) : BadgeCriteria()
    
    @Serializable
    data class LowDistractionStreak(val days: Int) : BadgeCriteria()
    
    @Serializable
    data class EarlyBird(val sessionsBeforeNoon: Int) : BadgeCriteria()
    
    @Serializable
    data class DeepDiver(val sessionMinutes: Int) : BadgeCriteria()
    
    @Serializable
    data class LimitMaster(val days: Int) : BadgeCriteria()
}

@Serializable
enum class StreakType {
    DAILY_FOCUS,
    DISTRACTION_FREE,
    EARLY_START
}

@Serializable
enum class BadgeTier {
    BRONZE, SILVER, GOLD, PLATINUM, SPECIAL
}

@Serializable
enum class AppCategory {
    GAMES,
    SOCIAL,
    PRODUCTIVITY,
    ENTERTAINMENT,
    EDUCATION,
    HEALTH,
    NEWS,
    SHOPPING,
    FINANCE,
    OTHER;
    
    companion object {
        fun fromSystemCategory(category: Int): AppCategory {
            return when (category) {
                0 -> GAMES
                1 -> SOCIAL
                2 -> PRODUCTIVITY
                3 -> ENTERTAINMENT
                4 -> EDUCATION
                5 -> HEALTH
                6 -> NEWS
                7 -> SHOPPING
                8 -> FINANCE
                else -> OTHER
            }
        }
    }
}

// Typealiases for serialization
@Serializable
data class AppUsageBreakdownSerializable(
    val packageName: String,
    val appName: String,
    val category: AppCategory,
    val usageSeconds: Long,
    val percentageOfTotal: Float
)
