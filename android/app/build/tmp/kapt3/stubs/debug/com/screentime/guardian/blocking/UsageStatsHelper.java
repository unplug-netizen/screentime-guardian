package com.screentime.guardian.blocking;

/**
 * Helper für UsageStatsManager API
 * Liest exakt die gleichen Daten wie Digital Wellbeing (±2% Toleranz)
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bJ\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0005\u00a8\u0006\u000e"}, d2 = {"Lcom/screentime/guardian/blocking/UsageStatsHelper;", "", "()V", "getAllUsageForToday", "", "", "", "context", "Landroid/content/Context;", "getUnlockCountToday", "", "getUsageByCategory", "getUsageForToday", "packageName", "app_debug"})
public final class UsageStatsHelper {
    @org.jetbrains.annotations.NotNull
    public static final com.screentime.guardian.blocking.UsageStatsHelper INSTANCE = null;
    
    private UsageStatsHelper() {
        super();
    }
    
    /**
     * Holt Usage-Zeit für heute in Millisekunden
     */
    public final long getUsageForToday(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String packageName) {
        return 0L;
    }
    
    /**
     * Holt alle Usage-Stats für heute
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.String, java.lang.Long> getAllUsageForToday(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return null;
    }
    
    /**
     * Anzahl der Entsperrungen heute
     */
    public final int getUnlockCountToday(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return 0;
    }
    
    /**
     * Kategorisiert Apps nach System-Kategorien
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.String, java.lang.Long> getUsageByCategory(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return null;
    }
}