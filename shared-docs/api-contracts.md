# API-Verträge & Domain Models

## ScreenTimeProvider Interface

```kotlin
// Android (Kotlin)
interface ScreenTimeProvider {
    // Echtzeit-Daten
    suspend fun getCurrentSession(): AppSession?
    suspend fun getTodayUsage(): DailyUsage
    suspend fun getAppUsage(packageName: String): AppUsageStats?
    
    // Historie (30 Tage)
    suspend fun getUsageHistory(days: Int = 30): List<DailyUsage>
    suspend fun getCategoryUsage(category: AppCategory, days: Int = 7): CategoryUsage
    
    // Limits
    suspend fun setAppLimit(packageName: String, limitMinutes: Int)
    suspend fun setCategoryLimit(category: AppCategory, limitMinutes: Int)
    suspend fun removeLimit(id: String)
    
    // Events
    fun onLimitReached(): Flow<LimitEvent>
    fun onUnlock(): Flow<UnlockEvent>
}
```

```swift
// iOS (Swift)
protocol ScreenTimeProvider: ObservableObject {
    func getCurrentSession() async throws -> AppSession?
    func getTodayUsage() async throws -> DailyUsage
    func getAppUsage(bundleIdentifier: String) async throws -> AppUsageStats?
    
    func getUsageHistory(days: Int) async throws -> [DailyUsage]
    func getCategoryUsage(_ category: AppCategory, days: Int) async throws -> CategoryUsage
    
    func setAppLimit(bundleIdentifier: String, limitMinutes: Int) async throws
    func setCategoryLimit(_ category: AppCategory, limitMinutes: Int) async throws
    func removeLimit(id: String) async throws
    
    var limitReachedPublisher: AnyPublisher<LimitEvent, Never> { get }
    var unlockPublisher: AnyPublisher<UnlockEvent, Never> { get }
}
```

## Daten-Modelle

### AppSession
```kotlin
data class AppSession(
    val id: String = UUID.randomUUID().toString(),
    val packageName: String,        // Android: package / iOS: bundle ID
    val appName: String,
    val category: AppCategory,      // SYSTEM-KATEGORIE (Games, Social, Productivity...)
    val startTime: Long,            // Unix timestamp (ms)
    val endTime: Long?,             // null = currently active
    val durationSeconds: Long       // Berechnet oder von API
)
```

### DailyUsage
```kotlin
data class DailyUsage(
    val date: LocalDate,            // YYYY-MM-DD
    val totalScreenTimeSeconds: Long,
    val unlockCount: Int,           // Anzahl Entsperrungen
    val appUsage: List<AppUsageBreakdown>,
    val categoryUsage: Map<AppCategory, Long>, // Sekunden pro Kategorie
    val focusSessionsCompleted: Int,
    val focusTimeSeconds: Long      // App-intern gemessen
)

data class AppUsageBreakdown(
    val packageName: String,
    val appName: String,
    val category: AppCategory,
    val usageSeconds: Long,
    val percentageOfTotal: Float
)
```

### LimitEntity
```kotlin
data class LimitEntity(
    val id: String = UUID.randomUUID().toString(),
    val type: LimitType,
    val targetId: String,           // packageName oder category.name
    val limitMinutes: Int,
    val createdAt: Long,
    val isActive: Boolean = true
)

enum class LimitType { APP, CATEGORY }
```

### AppCategory (System-Kategorien)
```kotlin
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
        fun fromSystemCategory(category: Int): AppCategory { /* Mapping */ }
    }
}
```

## Gamification Models

### Badge
```kotlin
data class Badge(
    val id: String,
    val name: String,
    val description: String,
    val tier: BadgeTier,
    val criteria: BadgeCriteria,
    val unlockedAt: Long? = null,
    val progress: Float = 0f        // 0.0 - 1.0
)

enum class BadgeTier { BRONZE, SILVER, GOLD, PLATINUM, SPECIAL }

sealed class BadgeCriteria {
    data class DailyFocusGoal(val hours: Int) : BadgeCriteria()
    data class StreakDays(val days: Int) : BadgeCriteria()
    data class TotalFocusTime(val hours: Int) : BadgeCriteria()
    data class LowDistractionStreak(val days: Int) : BadgeCriteria()
    data class EarlyBird(val sessionsBeforeNoon: Int) : BadgeCriteria()
    data class DeepDiver(val sessionMinutes: Int) : BadgeCriteria()
}
```

### Streak
```kotlin
data class Streak(
    val currentStreak: Int,         // Aktuelle Serie
    val longestStreak: Int,         // Rekord
    val lastActiveDate: LocalDate?, // Letzter Tag mit Aktivität
    val streakType: StreakType
)

enum class StreakType {
    DAILY_FOCUS,        // Jeden Tag Focus-Ziel erreicht
    DISTRACTION_FREE,   // Täglich unter Limit geblieben
    EARLY_START         // Vor 12 Uhr gestartet
}
```

## Social Models

### Challenge
```kotlin
data class Challenge(
    val id: String,
    val type: ChallengeType,
    val durationDays: Int,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val participants: List<ParticipantHash>, // SHA-256 gehashte IDs
    val status: ChallengeStatus
)

enum class ChallengeType {
    LEAST_SCREEN_TIME,      // Wer hat am wenigsten Screen Time
    MOST_FOCUS_TIME,        // Wer hat meiste Focus-Zeit
    LIMIT_ADHERENCE,        // Wer hält Limits am besten ein
    STREAK_MAINTAIN         // Wer hält Streak am längsten
}

typealias ParticipantHash = String // "User #8A3F" Format
```

### LeaderboardEntry
```kotlin
data class LeaderboardEntry(
    val userHash: ParticipantHash,
    val score: Int,                 // (100 - dailyScreenTimeHours) + focusMinutes
    val rank: Int,
    val trend: Trend                // UP, DOWN, STABLE
)
```

## Privacy & Sync

### UserSyncData
```kotlin
// NUR DIES wird zu Firebase gesendet
data class UserSyncData(
    val userHash: String,           // SHA-256(UID)
    val dailyStats: AggregatedDailyStats,
    val badgesUnlocked: List<String>, // Nur Badge-IDs
    val lastSyncTimestamp: Long
)

data class AggregatedDailyStats(
    val date: String,               // YYYY-MM-DD
    val totalScreenTimeMinutes: Int,
    val focusTimeMinutes: Int,
    val unlockCount: Int,
    val limitAdherenceRate: Float   // 0.0 - 1.0
)
```

## Events

```kotlin
data class LimitEvent(
    val limitId: String,
    val type: LimitType,
    val targetName: String,
    val limitMinutes: Int,
    val actualUsageMinutes: Int,
    val timestamp: Long
)

data class UnlockEvent(
    val limitId: String,
    val unlockedAt: Long,
    val unlockMethod: UnlockMethod
)

enum class UnlockMethod { BIOMETRIC, PIN, EMERGENCY }
```
