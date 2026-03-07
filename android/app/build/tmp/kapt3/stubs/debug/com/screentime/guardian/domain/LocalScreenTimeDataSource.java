package com.screentime.guardian.domain;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\'\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\b2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000eH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0019\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\fH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u0019\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/screentime/guardian/domain/LocalScreenTimeDataSource;", "", "deleteLimit", "", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchActiveLimits", "", "Lcom/screentime/guardian/domain/LimitEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchDailyUsage", "Lcom/screentime/guardian/domain/DailyUsage;", "forDate", "Ljava/time/LocalDate;", "(Ljava/time/LocalDate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchUsageHistory", "from", "to", "(Ljava/time/LocalDate;Ljava/time/LocalDate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveDailyUsage", "usage", "(Lcom/screentime/guardian/domain/DailyUsage;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveLimit", "limit", "(Lcom/screentime/guardian/domain/LimitEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface LocalScreenTimeDataSource {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object saveDailyUsage(@org.jetbrains.annotations.NotNull
    com.screentime.guardian.domain.DailyUsage usage, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object fetchUsageHistory(@org.jetbrains.annotations.NotNull
    java.time.LocalDate from, @org.jetbrains.annotations.NotNull
    java.time.LocalDate to, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.screentime.guardian.domain.DailyUsage>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object fetchDailyUsage(@org.jetbrains.annotations.NotNull
    java.time.LocalDate forDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.screentime.guardian.domain.DailyUsage> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object saveLimit(@org.jetbrains.annotations.NotNull
    com.screentime.guardian.domain.LimitEntity limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object fetchActiveLimits(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.screentime.guardian.domain.LimitEntity>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteLimit(@org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}