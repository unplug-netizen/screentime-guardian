package com.screentime.guardian.domain;

/**
 * Abstrahiert Android UsageStatsManager und iOS ScreenTime API
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u001b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ#\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u0011\u0010\u0014\u001a\u00020\u0015H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J!\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H&J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0019H&J\u0019\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ!\u0010 \u001a\u00020\u001e2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010!\u001a\u00020\u0010H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"J!\u0010#\u001a\u00020\u001e2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u0010H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006$"}, d2 = {"Lcom/screentime/guardian/domain/ScreenTimeProvider;", "", "getActiveLimits", "", "Lcom/screentime/guardian/domain/LimitEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAppUsage", "Lcom/screentime/guardian/domain/AppUsageStats;", "packageName", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCategoryUsage", "Lcom/screentime/guardian/domain/CategoryUsage;", "category", "Lcom/screentime/guardian/domain/AppCategory;", "days", "", "(Lcom/screentime/guardian/domain/AppCategory;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentSession", "Lcom/screentime/guardian/domain/AppSession;", "getTodayUsage", "Lcom/screentime/guardian/domain/DailyUsage;", "getUsageHistory", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onLimitReached", "Lkotlinx/coroutines/flow/Flow;", "Lcom/screentime/guardian/domain/LimitEvent;", "onUnlock", "Lcom/screentime/guardian/domain/UnlockEvent;", "removeLimit", "", "id", "setAppLimit", "limitMinutes", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setCategoryLimit", "app_debug"})
public abstract interface ScreenTimeProvider {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getCurrentSession(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.screentime.guardian.domain.AppSession> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTodayUsage(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.screentime.guardian.domain.DailyUsage> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAppUsage(@org.jetbrains.annotations.NotNull
    java.lang.String packageName, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.screentime.guardian.domain.AppUsageStats> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUsageHistory(int days, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.screentime.guardian.domain.DailyUsage>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getCategoryUsage(@org.jetbrains.annotations.NotNull
    com.screentime.guardian.domain.AppCategory category, int days, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.screentime.guardian.domain.CategoryUsage> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object setAppLimit(@org.jetbrains.annotations.NotNull
    java.lang.String packageName, int limitMinutes, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object setCategoryLimit(@org.jetbrains.annotations.NotNull
    com.screentime.guardian.domain.AppCategory category, int limitMinutes, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object removeLimit(@org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getActiveLimits(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.screentime.guardian.domain.LimitEntity>> $completion);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.screentime.guardian.domain.LimitEvent> onLimitReached();
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.screentime.guardian.domain.UnlockEvent> onUnlock();
    
    /**
     * Abstrahiert Android UsageStatsManager und iOS ScreenTime API
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}