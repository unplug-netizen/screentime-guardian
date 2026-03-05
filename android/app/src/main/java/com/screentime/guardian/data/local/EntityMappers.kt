package com.screentime.guardian.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.screentime.guardian.domain.AppCategory
import com.screentime.guardian.domain.AppSession
import com.screentime.guardian.domain.DailyUsage
import com.screentime.guardian.domain.LimitEntity
import com.screentime.guardian.gamification.Badge
import com.screentime.guardian.gamification.BadgeCriteria
import com.screentime.guardian.gamification.BadgeTier
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.LocalDate
import java.time.ZoneId

// Entity Mappers

fun DailyUsageEntity.toDomainModel(): DailyUsage {
    return DailyUsage(
        date = LocalDate.parse(this.date),
        totalScreenTimeSeconds = this.totalScreenTimeSeconds,
        unlockCount = this.unlockCount,
        appUsage = Json.decodeFromString(this.appUsageJson),
        categoryUsage = Json.decodeFromString(this.categoryUsageJson),
        focusSessionsCompleted = this.focusSessionsCompleted,
        focusTimeSeconds = this.focusTimeSeconds
    )
}

fun DailyUsage.toEntity(): DailyUsageEntity {
    return DailyUsageEntity(
        date = this.date.toString(),
        totalScreenTimeSeconds = this.totalScreenTimeSeconds,
        unlockCount = this.unlockCount,
        focusSessionsCompleted = this.focusSessionsCompleted,
        focusTimeSeconds = this.focusTimeSeconds,
        appUsageJson = Json.encodeToString(this.appUsage),
        categoryUsageJson = Json.encodeToString(this.categoryUsage)
    )
}

fun AppSessionEntity.toDomainModel(): AppSession {
    return AppSession(
        id = this.id,
        packageName = this.packageName,
        appName = this.appName,
        category = AppCategory.valueOf(this.category),
        startTime = this.startTime,
        endTime = this.endTime,
        durationSeconds = this.durationSeconds
    )
}

fun AppSession.toEntity(): AppSessionEntity {
    return AppSessionEntity(
        id = this.id,
        packageName = this.packageName,
        appName = this.appName,
        category = this.category.name,
        startTime = this.startTime,
        endTime = this.endTime,
        durationSeconds = this.durationSeconds,
        date = LocalDate.ofEpochDay(this.startTime / 86400000).toString()
    )
}

fun BadgeEntity.toDomainModel(): Badge {
    return Badge(
        id = this.id,
        name = this.name,
        description = this.description,
        tier = BadgeTier.valueOf(this.tier),
        criteria = Json.decodeFromString(this.criteriaJson),
        isUnlocked = this.isUnlocked,
        unlockedAt = this.unlockedAt,
        progress = this.progress
    )
}

fun Badge.toEntity(): BadgeEntity {
    return BadgeEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        tier = this.tier.name,
        criteriaJson = Json.encodeToString(this.criteria),
        isUnlocked = this.isUnlocked,
        unlockedAt = this.unlockedAt,
        progress = this.progress
    )
}
