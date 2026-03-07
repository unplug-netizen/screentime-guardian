package com.screentime.guardian.blocking;

/**
 * AccessibilityService für Overlay-Blockierung bei Limit-Erreichung
 * Zeigt Glassmorphism-Block-Screen statt "hackerigem" Overlay
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 !2\u00020\u0001:\u0001!B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0016\u0010\r\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J\u0018\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\nH\u0016J\b\u0010\u0019\u001a\u00020\nH\u0014J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\nH\u0002J\u0010\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u0010H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/screentime/guardian/blocking/AppBlockingService;", "Landroid/accessibilityservice/AccessibilityService;", "()V", "blockOverlay", "Lcom/screentime/guardian/blocking/BlockOverlayView;", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "windowManager", "Landroid/view/WindowManager;", "checkAppLimit", "", "packageName", "", "checkCurrentAppAgainstLimits", "limits", "", "Lcom/screentime/guardian/blocking/ActiveLimit;", "handleUnlock", "limitId", "method", "Lcom/screentime/guardian/blocking/UnlockMethod;", "onAccessibilityEvent", "event", "Landroid/view/accessibility/AccessibilityEvent;", "onInterrupt", "onServiceConnected", "onUnbind", "", "intent", "Landroid/content/Intent;", "removeBlockOverlay", "showBlockScreen", "limit", "Companion", "app_debug"})
public final class AppBlockingService extends android.accessibilityservice.AccessibilityService {
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    private android.view.WindowManager windowManager;
    @org.jetbrains.annotations.Nullable
    private com.screentime.guardian.blocking.BlockOverlayView blockOverlay;
    @org.jetbrains.annotations.NotNull
    private static final kotlinx.coroutines.flow.MutableSharedFlow<com.screentime.guardian.blocking.LimitReachedEvent> _limitReachedFlow = null;
    @org.jetbrains.annotations.NotNull
    private static final kotlinx.coroutines.flow.SharedFlow<com.screentime.guardian.blocking.LimitReachedEvent> limitReachedFlow = null;
    @org.jetbrains.annotations.NotNull
    private static final kotlinx.coroutines.flow.MutableSharedFlow<com.screentime.guardian.blocking.UnlockEvent> _unlockFlow = null;
    @org.jetbrains.annotations.NotNull
    private static final kotlinx.coroutines.flow.SharedFlow<com.screentime.guardian.blocking.UnlockEvent> unlockFlow = null;
    private static boolean isRunning = false;
    @org.jetbrains.annotations.NotNull
    public static final com.screentime.guardian.blocking.AppBlockingService.Companion Companion = null;
    
    public AppBlockingService() {
        super();
    }
    
    @java.lang.Override
    protected void onServiceConnected() {
    }
    
    @java.lang.Override
    public void onAccessibilityEvent(@org.jetbrains.annotations.NotNull
    android.view.accessibility.AccessibilityEvent event) {
    }
    
    @java.lang.Override
    public void onInterrupt() {
    }
    
    @java.lang.Override
    public boolean onUnbind(@org.jetbrains.annotations.Nullable
    android.content.Intent intent) {
        return false;
    }
    
    private final void checkAppLimit(java.lang.String packageName) {
    }
    
    private final void checkCurrentAppAgainstLimits(java.util.List<com.screentime.guardian.blocking.ActiveLimit> limits) {
    }
    
    private final void showBlockScreen(com.screentime.guardian.blocking.ActiveLimit limit) {
    }
    
    private final void removeBlockOverlay() {
    }
    
    private final void handleUnlock(java.lang.String limitId, com.screentime.guardian.blocking.UnlockMethod method) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000f\u00a8\u0006\u0012"}, d2 = {"Lcom/screentime/guardian/blocking/AppBlockingService$Companion;", "", "()V", "_limitReachedFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/screentime/guardian/blocking/LimitReachedEvent;", "_unlockFlow", "Lcom/screentime/guardian/blocking/UnlockEvent;", "<set-?>", "", "isRunning", "()Z", "limitReachedFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "getLimitReachedFlow", "()Lkotlinx/coroutines/flow/SharedFlow;", "unlockFlow", "getUnlockFlow", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final kotlinx.coroutines.flow.SharedFlow<com.screentime.guardian.blocking.LimitReachedEvent> getLimitReachedFlow() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final kotlinx.coroutines.flow.SharedFlow<com.screentime.guardian.blocking.UnlockEvent> getUnlockFlow() {
            return null;
        }
        
        public final boolean isRunning() {
            return false;
        }
    }
}