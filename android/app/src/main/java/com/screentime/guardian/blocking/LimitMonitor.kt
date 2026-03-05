package com.screentime.guardian.blocking

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Verwaltet aktive Limits und überwacht deren Einhaltung
 */
object LimitMonitor {
    
    private val _activeLimits = MutableStateFlow<List<ActiveLimit>>(emptyList())
    val activeLimits: StateFlow<List<ActiveLimit>> = _activeLimits
    
    private val unlockCounts = mutableMapOf<String, Int>()
    
    fun setLimits(limits: List<ActiveLimit>) {
        _activeLimits.value = limits
    }
    
    fun getLimitForPackage(packageName: String): ActiveLimit? {
        return _activeLimits.value.find { it.targetId == packageName }
    }
    
    fun recordUnlock(limitId: String) {
        unlockCounts[limitId] = (unlockCounts[limitId] ?: 0) + 1
    }
    
    fun getUnlockCount(limitId: String): Int {
        return unlockCounts[limitId] ?: 0
    }
    
    fun clearDailyStats() {
        unlockCounts.clear()
    }
}

data class ActiveLimit(
    val id: String,
    val type: LimitType,
    val targetId: String, // packageName oder category
    val targetName: String,
    val limitMinutes: Int,
    val currentUsageMinutes: Int = 0
)

enum class LimitType {
    APP, CATEGORY
}
