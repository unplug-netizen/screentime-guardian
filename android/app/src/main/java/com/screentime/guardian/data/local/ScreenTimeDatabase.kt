package com.screentime.guardian.data.local

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.ZoneId

/**
 * Room Database für Local-First Cache
 * Speichert 30 Tage Historie
 */
@Database(
    entities = [
        DailyUsageEntity::class,
        AppSessionEntity::class,
        LimitEntity::class,
        BadgeEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ScreenTimeDatabase : RoomDatabase() {
    abstract fun dailyUsageDao(): DailyUsageDao
    abstract fun appSessionDao(): AppSessionDao
    abstract fun limitDao(): LimitDao
    abstract fun badgeDao(): BadgeDao

    companion object {
        @Volatile
        private var INSTANCE: ScreenTimeDatabase? = null

        fun getDatabase(context: Context): ScreenTimeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ScreenTimeDatabase::class.java,
                    "screentime_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

// Entities

@Entity(tableName = "daily_usage")
data class DailyUsageEntity(
    @PrimaryKey
    val date: String, // YYYY-MM-DD
    val totalScreenTimeSeconds: Long,
    val unlockCount: Int,
    val focusSessionsCompleted: Int,
    val focusTimeSeconds: Long,
    val appUsageJson: String, // JSON serialized
    val categoryUsageJson: String // JSON serialized
)

@Entity(tableName = "app_sessions")
data class AppSessionEntity(
    @PrimaryKey
    val id: String,
    val packageName: String,
    val appName: String,
    val category: String,
    val startTime: Long,
    val endTime: Long?,
    val durationSeconds: Long,
    val date: String // YYYY-MM-DD für schnellen Zugriff
)

@Entity(tableName = "limits")
data class LimitEntity(
    @PrimaryKey
    val id: String,
    val type: String, // APP oder CATEGORY
    val targetId: String,
    val targetName: String,
    val limitMinutes: Int,
    val createdAt: Long,
    val isActive: Boolean = true
)

@Entity(tableName = "badges")
data class BadgeEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val tier: String,
    val criteriaJson: String, // Serialized BadgeCriteria
    val isUnlocked: Boolean = false,
    val unlockedAt: Long? = null,
    val progress: Float = 0f
)

// DAOs

@Dao
interface DailyUsageDao {
    @Query("SELECT * FROM daily_usage WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getUsageHistory(startDate: String, endDate: String): Flow<List<DailyUsageEntity>>

    @Query("SELECT * FROM daily_usage WHERE date = :date")
    suspend fun getDailyUsage(date: String): DailyUsageEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyUsage(usage: DailyUsageEntity)

    @Query("DELETE FROM daily_usage WHERE date < :cutoffDate")
    suspend fun deleteOldData(cutoffDate: String)
}

@Dao
interface AppSessionDao {
    @Query("SELECT * FROM app_sessions WHERE date = :date ORDER BY startTime DESC")
    suspend fun getSessionsForDate(date: String): List<AppSessionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: AppSessionEntity)

    @Query("UPDATE app_sessions SET endTime = :endTime, durationSeconds = :duration WHERE id = :sessionId")
    suspend fun updateSessionEnd(sessionId: String, endTime: Long, duration: Long)
}

@Dao
interface LimitDao {
    @Query("SELECT * FROM limits WHERE isActive = 1")
    fun getActiveLimits(): Flow<List<LimitEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLimit(limit: LimitEntity)

    @Query("UPDATE limits SET isActive = 0 WHERE id = :limitId")
    suspend fun deactivateLimit(limitId: String)
}

@Dao
interface BadgeDao {
    @Query("SELECT * FROM badges ORDER BY tier, id")
    fun getAllBadges(): Flow<List<BadgeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBadge(badge: BadgeEntity)

    @Query("UPDATE badges SET isUnlocked = 1, unlockedAt = :timestamp, progress = 1.0 WHERE id = :badgeId")
    suspend fun unlockBadge(badgeId: String, timestamp: Long)

    @Query("UPDATE badges SET progress = :progress WHERE id = :badgeId")
    suspend fun updateProgress(badgeId: String, progress: Float)
}

// Type Converters

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {
        return value?.let {
            java.time.Instant.ofEpochMilli(it)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): Long? {
        return date?.atStartOfDay(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()
    }
}
