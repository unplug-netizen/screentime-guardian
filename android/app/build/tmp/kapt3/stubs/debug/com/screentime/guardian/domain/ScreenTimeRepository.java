package com.screentime.guardian.domain;

/**
 * Local-First Repository mit 30 Tagen Historie
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J!\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\b\b\u0002\u0010\u0014\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0011H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J\f\u0010\u001a\u001a\u00020\u001b*\u00020\u0011H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001c"}, d2 = {"Lcom/screentime/guardian/domain/ScreenTimeRepository;", "", "localDataSource", "Lcom/screentime/guardian/domain/LocalScreenTimeDataSource;", "remoteDataSource", "Lcom/screentime/guardian/domain/RemoteScreenTimeDataSource;", "(Lcom/screentime/guardian/domain/LocalScreenTimeDataSource;Lcom/screentime/guardian/domain/RemoteScreenTimeDataSource;)V", "calculateStreak", "Lcom/screentime/guardian/domain/Streak;", "type", "Lcom/screentime/guardian/domain/StreakType;", "goalMinutes", "", "(Lcom/screentime/guardian/domain/StreakType;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "didMeetStreakGoal", "", "day", "Lcom/screentime/guardian/domain/DailyUsage;", "getUsageHistory", "", "days", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveDailyUsage", "", "usage", "(Lcom/screentime/guardian/domain/DailyUsage;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toSyncData", "Lcom/screentime/guardian/domain/AggregatedDailyStats;", "app_debug"})
public final class ScreenTimeRepository {
    @org.jetbrains.annotations.NotNull
    private final com.screentime.guardian.domain.LocalScreenTimeDataSource localDataSource = null;
    @org.jetbrains.annotations.Nullable
    private final com.screentime.guardian.domain.RemoteScreenTimeDataSource remoteDataSource = null;
    
    public ScreenTimeRepository(@org.jetbrains.annotations.NotNull
    com.screentime.guardian.domain.LocalScreenTimeDataSource localDataSource, @org.jetbrains.annotations.Nullable
    com.screentime.guardian.domain.RemoteScreenTimeDataSource remoteDataSource) {
        super();
    }
    
    /**
     * Speichert DailyUsage lokal und trigger Sync
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object saveDailyUsage(@org.jetbrains.annotations.NotNull
    com.screentime.guardian.domain.DailyUsage usage, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Holt Historie für die letzten X Tage
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getUsageHistory(int days, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.screentime.guardian.domain.DailyUsage>> $completion) {
        return null;
    }
    
    /**
     * Berechnet Streaks basierend auf lokalen Daten
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object calculateStreak(@org.jetbrains.annotations.NotNull
    com.screentime.guardian.domain.StreakType type, int goalMinutes, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.screentime.guardian.domain.Streak> $completion) {
        return null;
    }
    
    private final boolean didMeetStreakGoal(com.screentime.guardian.domain.DailyUsage day, com.screentime.guardian.domain.StreakType type, int goalMinutes) {
        return false;
    }
    
    private final com.screentime.guardian.domain.AggregatedDailyStats toSyncData(com.screentime.guardian.domain.DailyUsage $this$toSyncData) {
        return null;
    }
}