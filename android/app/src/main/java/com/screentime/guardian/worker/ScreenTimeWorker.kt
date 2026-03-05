package com.screentime.guardian.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.screentime.guardian.MainActivity
import com.screentime.guardian.blocking.UsageStatsHelper
import com.screentime.guardian.data.local.ScreenTimeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Background Worker für:
 * - Tägliche Stats-Aggregation
 * - Limit-Checks
 * - Badge-Prüfungen
 * - Sync zu Firebase
 */
class ScreenTimeWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    companion object {
        const val WORK_NAME = "screentime_daily_worker"
        const val CHANNEL_ID = "screentime_alerts"
        const val NOTIFICATION_ID_LIMIT = 1001
        const val NOTIFICATION_ID_BADGE = 1002
    }

    private val database = ScreenTimeDatabase.getDatabase(context)
    private val usageDao = database.dailyUsageDao()

    override suspend fun doWork(): Result {
        return try {
            // 1. Heutige Stats aggregieren
            aggregateTodayStats()

            // 2. Limits prüfen
            checkLimits()

            // 3. Alte Daten löschen (>30 Tage)
            cleanupOldData()

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    private suspend fun aggregateTodayStats() {
        withContext(Dispatchers.IO) {
            val today = LocalDate.now()
            val dateStr = today.format(DateTimeFormatter.ISO_DATE)

            val usage = UsageStatsHelper.getAllUsageForToday(applicationContext)
            val unlockCount = UsageStatsHelper.getUnlockCountToday(applicationContext)

            val totalSeconds = usage.values.sum()

            // In DB speichern
            val entity = com.screentime.guardian.data.local.DailyUsageEntity(
                date = dateStr,
                totalScreenTimeSeconds = totalSeconds,
                unlockCount = unlockCount,
                focusSessionsCompleted = 0, // Wird aus anderer Quelle geladen
                focusTimeSeconds = 0,
                appUsageJson = "{}", // TODO: Serialize properly
                categoryUsageJson = "{}"
            )

            usageDao.insertDailyUsage(entity)
        }
    }

    private suspend fun checkLimits() {
        // Prüfe ob Limits erreicht wurden und sende Notification
        // Implementation...
    }

    private suspend fun cleanupOldData() {
        withContext(Dispatchers.IO) {
            val cutoff = LocalDate.now().minusDays(31)
            usageDao.deleteOldData(cutoff.format(DateTimeFormatter.ISO_DATE))
        }
    }

    private fun showLimitNotification(appName: String) {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel()

        val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle("Zeitlimit erreicht")
            .setContentText("Du hast dein Tageslimit für $appName erreicht")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(NOTIFICATION_ID_LIMIT, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "ScreenTime Alerts",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Benachrichtigungen für Zeitlimits und Erfolge"
            }

            val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
