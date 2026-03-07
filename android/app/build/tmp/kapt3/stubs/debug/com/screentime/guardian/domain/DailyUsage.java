package com.screentime.guardian.domain;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\f\u0012\u0006\u0010\u000e\u001a\u00020\u0007\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0010J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0005H\u00c6\u0003J\t\u0010)\u001a\u00020\u0007H\u00c6\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0003J\u0015\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\fH\u00c6\u0003J\t\u0010,\u001a\u00020\u0007H\u00c6\u0003J\t\u0010-\u001a\u00020\u0005H\u00c6\u0003Ja\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00102\u001a\u00020\u0007H\u00d6\u0001J\t\u00103\u001a\u00020 H\u00d6\u0001R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000e\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u001a8F\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020 8F\u00a2\u0006\u0006\u001a\u0004\b!\u0010\"R\u0011\u0010#\u001a\u00020\u001a8F\u00a2\u0006\u0006\u001a\u0004\b$\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001eR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0018\u00a8\u00064"}, d2 = {"Lcom/screentime/guardian/domain/DailyUsage;", "", "date", "Ljava/time/LocalDate;", "totalScreenTimeSeconds", "", "unlockCount", "", "appUsage", "", "Lcom/screentime/guardian/domain/AppUsageBreakdown;", "categoryUsage", "", "Lcom/screentime/guardian/domain/AppCategory;", "focusSessionsCompleted", "focusTimeSeconds", "(Ljava/time/LocalDate;JILjava/util/List;Ljava/util/Map;IJ)V", "getAppUsage", "()Ljava/util/List;", "getCategoryUsage", "()Ljava/util/Map;", "getDate", "()Ljava/time/LocalDate;", "getFocusSessionsCompleted", "()I", "focusTimeMinutes", "", "getFocusTimeMinutes", "()D", "getFocusTimeSeconds", "()J", "id", "", "getId", "()Ljava/lang/String;", "totalScreenTimeHours", "getTotalScreenTimeHours", "getTotalScreenTimeSeconds", "getUnlockCount", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class DailyUsage {
    @org.jetbrains.annotations.NotNull
    private final java.time.LocalDate date = null;
    private final long totalScreenTimeSeconds = 0L;
    private final int unlockCount = 0;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.screentime.guardian.domain.AppUsageBreakdown> appUsage = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.Map<com.screentime.guardian.domain.AppCategory, java.lang.Long> categoryUsage = null;
    private final int focusSessionsCompleted = 0;
    private final long focusTimeSeconds = 0L;
    
    public DailyUsage(@org.jetbrains.annotations.NotNull
    java.time.LocalDate date, long totalScreenTimeSeconds, int unlockCount, @org.jetbrains.annotations.NotNull
    java.util.List<com.screentime.guardian.domain.AppUsageBreakdown> appUsage, @org.jetbrains.annotations.NotNull
    java.util.Map<com.screentime.guardian.domain.AppCategory, java.lang.Long> categoryUsage, int focusSessionsCompleted, long focusTimeSeconds) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.time.LocalDate getDate() {
        return null;
    }
    
    public final long getTotalScreenTimeSeconds() {
        return 0L;
    }
    
    public final int getUnlockCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.screentime.guardian.domain.AppUsageBreakdown> getAppUsage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<com.screentime.guardian.domain.AppCategory, java.lang.Long> getCategoryUsage() {
        return null;
    }
    
    public final int getFocusSessionsCompleted() {
        return 0;
    }
    
    public final long getFocusTimeSeconds() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getId() {
        return null;
    }
    
    public final double getTotalScreenTimeHours() {
        return 0.0;
    }
    
    public final double getFocusTimeMinutes() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.time.LocalDate component1() {
        return null;
    }
    
    public final long component2() {
        return 0L;
    }
    
    public final int component3() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.screentime.guardian.domain.AppUsageBreakdown> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<com.screentime.guardian.domain.AppCategory, java.lang.Long> component5() {
        return null;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final long component7() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.screentime.guardian.domain.DailyUsage copy(@org.jetbrains.annotations.NotNull
    java.time.LocalDate date, long totalScreenTimeSeconds, int unlockCount, @org.jetbrains.annotations.NotNull
    java.util.List<com.screentime.guardian.domain.AppUsageBreakdown> appUsage, @org.jetbrains.annotations.NotNull
    java.util.Map<com.screentime.guardian.domain.AppCategory, java.lang.Long> categoryUsage, int focusSessionsCompleted, long focusTimeSeconds) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
}