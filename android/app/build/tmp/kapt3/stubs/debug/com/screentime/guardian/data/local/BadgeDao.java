package com.screentime.guardian.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0019\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ!\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ!\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lcom/screentime/guardian/data/local/BadgeDao;", "", "getAllBadges", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/screentime/guardian/data/local/BadgeEntity;", "insertBadge", "", "badge", "(Lcom/screentime/guardian/data/local/BadgeEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unlockBadge", "badgeId", "", "timestamp", "", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProgress", "progress", "", "(Ljava/lang/String;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao
public abstract interface BadgeDao {
    
    @androidx.room.Query(value = "SELECT * FROM badges ORDER BY tier, id")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.screentime.guardian.data.local.BadgeEntity>> getAllBadges();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertBadge(@org.jetbrains.annotations.NotNull
    com.screentime.guardian.data.local.BadgeEntity badge, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE badges SET isUnlocked = 1, unlockedAt = :timestamp, progress = 1.0 WHERE id = :badgeId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object unlockBadge(@org.jetbrains.annotations.NotNull
    java.lang.String badgeId, long timestamp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE badges SET progress = :progress WHERE id = :badgeId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateProgress(@org.jetbrains.annotations.NotNull
    java.lang.String badgeId, float progress, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}