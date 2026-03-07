package com.screentime.guardian.gamification;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\nH\u00c6\u0003J;\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u001b\u001a\u00020\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00d6\u0001J\u0016\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0005J\t\u0010\"\u001a\u00020#H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011\u00a8\u0006$"}, d2 = {"Lcom/screentime/guardian/gamification/DailyActivity;", "", "date", "Ljava/time/LocalDate;", "focusTimeMinutes", "", "distractionPercentage", "", "sessionsBeforeNoon", "limitsAdhered", "", "(Ljava/time/LocalDate;IFIZ)V", "getDate", "()Ljava/time/LocalDate;", "getDistractionPercentage", "()F", "getFocusTimeMinutes", "()I", "getLimitsAdhered", "()Z", "getSessionsBeforeNoon", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "meetsStreakGoal", "type", "Lcom/screentime/guardian/gamification/StreakType;", "goalMinutes", "toString", "", "app_debug"})
public final class DailyActivity {
    @org.jetbrains.annotations.NotNull
    private final java.time.LocalDate date = null;
    private final int focusTimeMinutes = 0;
    private final float distractionPercentage = 0.0F;
    private final int sessionsBeforeNoon = 0;
    private final boolean limitsAdhered = false;
    
    public DailyActivity(@org.jetbrains.annotations.NotNull
    java.time.LocalDate date, int focusTimeMinutes, float distractionPercentage, int sessionsBeforeNoon, boolean limitsAdhered) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.time.LocalDate getDate() {
        return null;
    }
    
    public final int getFocusTimeMinutes() {
        return 0;
    }
    
    public final float getDistractionPercentage() {
        return 0.0F;
    }
    
    public final int getSessionsBeforeNoon() {
        return 0;
    }
    
    public final boolean getLimitsAdhered() {
        return false;
    }
    
    public final boolean meetsStreakGoal(@org.jetbrains.annotations.NotNull
    com.screentime.guardian.gamification.StreakType type, int goalMinutes) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.time.LocalDate component1() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final float component3() {
        return 0.0F;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.screentime.guardian.gamification.DailyActivity copy(@org.jetbrains.annotations.NotNull
    java.time.LocalDate date, int focusTimeMinutes, float distractionPercentage, int sessionsBeforeNoon, boolean limitsAdhered) {
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