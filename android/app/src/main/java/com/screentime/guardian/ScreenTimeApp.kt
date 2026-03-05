package com.screentime.guardian

import android.app.Application
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.screentime.guardian.data.local.ScreenTimeDatabase
import com.screentime.guardian.worker.ScreenTimeWorker
import java.util.concurrent.TimeUnit

class ScreenTimeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        
        // Initialize database
        ScreenTimeDatabase.getDatabase(this)
        
        // Schedule background worker
        scheduleDailyWorker()
    }

    private fun scheduleDailyWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresBatteryNotLow(false)
            .build()

        val dailyWorkRequest = PeriodicWorkRequestBuilder<ScreenTimeWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            ScreenTimeWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            dailyWorkRequest
        )
    }
}
