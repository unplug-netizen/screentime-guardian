package com.screentime.guardian.blocking

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent
import android.view.WindowManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

/**
 * AccessibilityService für Overlay-Blockierung bei Limit-Erreichung
 * Zeigt Glassmorphism-Block-Screen statt "hackerigem" Overlay
 */
class AppBlockingService : AccessibilityService() {
    
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private lateinit var windowManager: WindowManager
    private var blockOverlay: BlockOverlayView? = null
    
    companion object {
        private val _limitReachedFlow = MutableSharedFlow<LimitReachedEvent>()
        val limitReachedFlow: SharedFlow<LimitReachedEvent> = _limitReachedFlow
        
        private val _unlockFlow = MutableSharedFlow<UnlockEvent>()
        val unlockFlow: SharedFlow<UnlockEvent> = _unlockFlow
        
        var isRunning = false
            private set
    }
    
    override fun onServiceConnected() {
        super.onServiceConnected()
        isRunning = true
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        
        serviceScope.launch {
            LimitMonitor.activeLimits.collect { limits ->
                checkCurrentAppAgainstLimits(limits)
            }
        }
    }
    
    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val packageName = event.packageName?.toString() ?: return
            checkAppLimit(packageName)
        }
    }
    
    override fun onInterrupt() {
        // Service unterbrochen
    }
    
    override fun onUnbind(intent: Intent?): Boolean {
        isRunning = false
        serviceScope.cancel()
        removeBlockOverlay()
        return super.onUnbind(intent)
    }
    
    private fun checkAppLimit(packageName: String) {
        val limit = LimitMonitor.getLimitForPackage(packageName) ?: return
        val usage = UsageStatsHelper.getUsageForToday(this, packageName)
        
        if (usage >= limit.limitMinutes * 60 * 1000) {
            showBlockScreen(limit)
        }
    }
    
    private fun checkCurrentAppAgainstLimits(limits: List<ActiveLimit>) {
        // Wird von LimitMonitor getriggert
    }
    
    private fun showBlockScreen(limit: ActiveLimit) {
        if (blockOverlay != null) return // Bereits geblockt
        
        serviceScope.launch {
            _limitReachedFlow.emit(LimitReachedEvent(
                limitId = limit.id,
                appName = limit.targetName,
                limitMinutes = limit.limitMinutes
            ))
        }
        
        blockOverlay = BlockOverlayView(this).apply {
            setup(
                appName = limit.targetName,
                limitMinutes = limit.limitMinutes,
                onUnlock = { method ->
                    handleUnlock(limit.id, method)
                }
            )
        }
        
        BlockOverlayManager.showOverlay(windowManager, blockOverlay!!)
    }
    
    private fun removeBlockOverlay() {
        blockOverlay?.let {
            BlockOverlayManager.removeOverlay(windowManager, it)
            blockOverlay = null
        }
    }
    
    private fun handleUnlock(limitId: String, method: UnlockMethod) {
        serviceScope.launch {
            _unlockFlow.emit(UnlockEvent(limitId, System.currentTimeMillis(), method))
        }
        
        // Lokalen Counter erhöhen (kein "Schuld-Log")
        LimitMonitor.recordUnlock(limitId)
        
        removeBlockOverlay()
    }
}

data class LimitReachedEvent(
    val limitId: String,
    val appName: String,
    val limitMinutes: Int
)

data class UnlockEvent(
    val limitId: String,
    val timestamp: Long,
    val method: UnlockMethod
)

enum class UnlockMethod {
    BIOMETRIC, PIN, EMERGENCY
}
