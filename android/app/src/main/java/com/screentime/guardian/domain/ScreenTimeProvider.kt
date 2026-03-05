package com.screentime.guardian.domain

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.util.UUID

/**
 * Abstrahiert Android UsageStatsManager und iOS ScreenTime API
 */
interface ScreenTimeProvider {
    // Echtzeit-Daten
    suspend fun getCurrentSession(): AppSession?
    suspend fun getTodayUsage(): DailyUsage
    suspend fun getAppUsage(packageName: String): AppUsageStats?
    
    // Historie (30 Tage Local Cache)
    suspend fun getUsageHistory(days: Int = 30): List<DailyUsage>
    suspend fun getCategoryUsage(category: AppCategory, days: Int = 7): CategoryUsage
    
    // Limits
    suspend fun setAppLimit(packageName: String, limitMinutes: Int)
    suspend fun setCategoryLimit(category: AppCategory, limitMinutes: Int)
    suspend fun removeLimit(id: String)
    suspend fun getActiveLimits(): List<LimitEntity>
    
    // Events
    fun onLimitReached(): Flow<LimitEvent>
    fun onUnlock(): Flow<UnlockEvent>
}

// Data Models

data class AppSession(
    val id: String = UUID.randomUUID().toString(),
    val packageName: String,
    val appName: String,
    val category: AppCategory,
    val startTime: Long, // Unix timestamp (ms)
    val endTime: Long?, // null = currently active
    val durationSeconds: Long
) {
    val isActive: Boolean get() = endTime == null
}

data class DailyUsage(
    val date: LocalDate,
    val totalScreenTimeSeconds: Long,
    val unlockCount: Int,
    val appUsage: List<AppUsageBreakdown>,
    val categoryUsage: Map<AppCategory, Long>,
    val focusSessionsCompleted: Int,
    val focusTimeSeconds: Long
) {
    val id: String get() = date.toString() // YYYY-MM-DD
    val totalScreenTimeHours: Double get() = totalScreenTimeSeconds / 3600.0
    val focusTimeMinutes: Double get() = focusTimeSeconds / 60.0
}

data class AppUsageBreakdown(
    val packageName: String,
    val appName: String,
    val category: AppCategory,
    val usageSeconds: Long,
    val percentageOfTotal: Float
)

data class AppUsageStats(
    val packageName: String,
    val appName: String,
    val category: AppCategory,
    val totalUsageToday: Long,
    val lastUsed: Long?
)

data class CategoryUsage(
    val category: AppCategory,
    val totalSeconds: Long,
    val topApps: List<AppUsageBreakdown>
)

// Limit Models

data class LimitEntity(
    val id: String = UUID.randomUUID().toString(),
    val type: LimitType,
    val targetId: String, // packageName oder category.name
    val targetName: String,
    val limitMinutes: Int,
    val createdAt: Long = System.currentTimeMillis(),
    val isActive: Boolean = true,
    val currentUsageMinutes: Int? = null
)

enum class LimitType {
    APP, CATEGORY
}

// App Category (System-Kategorien)

enum class AppCategory(val displayName: String, val icon: String) {
    GAMES("Games", "videogame_asset"),
    SOCIAL("Social", "chat_bubble"),
    PRODUCTIVITY("Productivity", "check_circle"),
    ENTERTAINMENT("Entertainment", "play_circle"),
    EDUCATION("Education", "school"),
    HEALTH("Health", "favorite"),
    NEWS("News", "newspaper"),
    SHOPPING("Shopping", "shopping_bag"),
    FINANCE("Finance", "account_balance"),
    OTHER("Other", "apps");
    
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

// Events

data class LimitEvent(
    val limitId: String,
    val type: LimitType,
    val targetName: String,
    val limitMinutes: Int,
    val actualUsageMinutes: Int,
    val timestamp: Long = System.currentTimeMillis()
)

data class UnlockEvent(
    val limitId: String,
    val unlockedAt: Long = System.currentTimeMillis(),
    val unlockMethod: UnlockMethod
)

enum class UnlockMethod {
    BIOMETRIC, PIN, EMERGENCY
}
