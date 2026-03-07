package com.screentime.guardian.gamification;

/**
 * Streak-Berechnung mit einfachen Tagesvergleichen
 * Keine Zeitzonen-Komplexität - 00:00-23:59 Systemzeit
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ \u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0007J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\tH\u0002\u00a8\u0006\u0013"}, d2 = {"Lcom/screentime/guardian/gamification/StreakCalculator;", "", "()V", "calculateStreak", "Lcom/screentime/guardian/gamification/StreakResult;", "history", "", "Lcom/screentime/guardian/gamification/DailyActivity;", "streakType", "Lcom/screentime/guardian/gamification/StreakType;", "goalMinutes", "", "canExtendStreakToday", "", "lastActiveDate", "Ljava/time/LocalDate;", "todayProgress", "getGoalForType", "type", "app_debug"})
public final class StreakCalculator {
    
    public StreakCalculator() {
        super();
    }
    
    /**
     * Berechnet aktuelle und längste Streak
     */
    @org.jetbrains.annotations.NotNull
    public final com.screentime.guardian.gamification.StreakResult calculateStreak(@org.jetbrains.annotations.NotNull
    java.util.List<com.screentime.guardian.gamification.DailyActivity> history, @org.jetbrains.annotations.NotNull
    com.screentime.guardian.gamification.StreakType streakType, int goalMinutes) {
        return null;
    }
    
    /**
     * Prüft ob Streak heute erweitert werden kann
     */
    public final boolean canExtendStreakToday(@org.jetbrains.annotations.Nullable
    java.time.LocalDate lastActiveDate, @org.jetbrains.annotations.NotNull
    com.screentime.guardian.gamification.StreakType streakType, @org.jetbrains.annotations.NotNull
    com.screentime.guardian.gamification.DailyActivity todayProgress) {
        return false;
    }
    
    private final int getGoalForType(com.screentime.guardian.gamification.StreakType type) {
        return 0;
    }
}