package com.screentime.guardian.domain;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u0019\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000b"}, d2 = {"Lcom/screentime/guardian/domain/RemoteScreenTimeDataSource;", "", "fetchLeaderboard", "", "Lcom/screentime/guardian/domain/LeaderboardEntry;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncDailyStats", "", "stats", "Lcom/screentime/guardian/domain/AggregatedDailyStats;", "(Lcom/screentime/guardian/domain/AggregatedDailyStats;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface RemoteScreenTimeDataSource {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object syncDailyStats(@org.jetbrains.annotations.NotNull
    com.screentime.guardian.domain.AggregatedDailyStats stats, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object fetchLeaderboard(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.screentime.guardian.domain.LeaderboardEntry>> $completion);
}