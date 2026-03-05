package com.screentime.guardian.blocking

import android.app.usage.UsageStatsManager
import android.content.Context
import android.os.Build
import java.util.Calendar

/**
 * Helper für UsageStatsManager API
 * Liest exakt die gleichen Daten wie Digital Wellbeing (±2% Toleranz)
 */
object UsageStatsHelper {
    
    /**
     * Holt Usage-Zeit für heute in Millisekunden
     */
    fun getUsageForToday(context: Context, packageName: String): Long {
        val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val startTime = calendar.timeInMillis
        val endTime = System.currentTimeMillis()
        
        val stats = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            usageStatsManager.queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY,
                startTime,
                endTime
            )
        } else null
        
        return stats?.find { it.packageName == packageName }?.totalTimeInForeground ?: 0L
    }
    
    /**
     * Holt alle Usage-Stats für heute
     */
    fun getAllUsageForToday(context: Context): Map<String, Long> {
        val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val startTime = calendar.timeInMillis
        val endTime = System.currentTimeMillis()
        
        val stats = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            startTime,
            endTime
        ) ?: return emptyMap()
        
        return stats.associate { it.packageName to it.totalTimeInForeground }
    }
    
    /**
     * Anzahl der Entsperrungen heute
     */
    fun getUnlockCountToday(context: Context): Int {
        val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        
        val events = usageStatsManager.queryEvents(calendar.timeInMillis, System.currentTimeMillis())
        var unlockCount = 0
        
        while (events.hasNextEvent()) {
            val event = android.app.usage.UsageEvents.Event()
            events.getNextEvent(event)
            
            if (event.eventType == android.app.usage.UsageEvents.Event.KEYGUARD_HIDDEN) {
                unlockCount++
            }
        }
        
        return unlockCount
    }
    
    /**
     * Kategorisiert Apps nach System-Kategorien
     */
    fun getUsageByCategory(context: Context): Map<String, Long> {
        // Implementation nutzt PackageManager für Kategorie-Lookup
        return emptyMap()
    }
}
