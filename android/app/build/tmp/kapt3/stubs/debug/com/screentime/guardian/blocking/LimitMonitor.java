package com.screentime.guardian.blocking;

/**
 * Verwaltet aktive Limits und überwacht deren Einhaltung
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0012\u001a\u00020\rJ\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\rJ\u000e\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\rJ\u0014\u0010\u0016\u001a\u00020\u00102\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/screentime/guardian/blocking/LimitMonitor;", "", "()V", "_activeLimits", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/screentime/guardian/blocking/ActiveLimit;", "activeLimits", "Lkotlinx/coroutines/flow/StateFlow;", "getActiveLimits", "()Lkotlinx/coroutines/flow/StateFlow;", "unlockCounts", "", "", "", "clearDailyStats", "", "getLimitForPackage", "packageName", "getUnlockCount", "limitId", "recordUnlock", "setLimits", "limits", "app_debug"})
public final class LimitMonitor {
    @org.jetbrains.annotations.NotNull
    private static final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.screentime.guardian.blocking.ActiveLimit>> _activeLimits = null;
    @org.jetbrains.annotations.NotNull
    private static final kotlinx.coroutines.flow.StateFlow<java.util.List<com.screentime.guardian.blocking.ActiveLimit>> activeLimits = null;
    @org.jetbrains.annotations.NotNull
    private static final java.util.Map<java.lang.String, java.lang.Integer> unlockCounts = null;
    @org.jetbrains.annotations.NotNull
    public static final com.screentime.guardian.blocking.LimitMonitor INSTANCE = null;
    
    private LimitMonitor() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.screentime.guardian.blocking.ActiveLimit>> getActiveLimits() {
        return null;
    }
    
    public final void setLimits(@org.jetbrains.annotations.NotNull
    java.util.List<com.screentime.guardian.blocking.ActiveLimit> limits) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.screentime.guardian.blocking.ActiveLimit getLimitForPackage(@org.jetbrains.annotations.NotNull
    java.lang.String packageName) {
        return null;
    }
    
    public final void recordUnlock(@org.jetbrains.annotations.NotNull
    java.lang.String limitId) {
    }
    
    public final int getUnlockCount(@org.jetbrains.annotations.NotNull
    java.lang.String limitId) {
        return 0;
    }
    
    public final void clearDailyStats() {
    }
}