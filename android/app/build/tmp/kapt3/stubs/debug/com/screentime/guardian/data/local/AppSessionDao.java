package com.screentime.guardian.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u001f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ)\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lcom/screentime/guardian/data/local/AppSessionDao;", "", "getSessionsForDate", "", "Lcom/screentime/guardian/data/local/AppSessionEntity;", "date", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertSession", "", "session", "(Lcom/screentime/guardian/data/local/AppSessionEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSessionEnd", "sessionId", "endTime", "", "duration", "(Ljava/lang/String;JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao
public abstract interface AppSessionDao {
    
    @androidx.room.Query(value = "SELECT * FROM app_sessions WHERE date = :date ORDER BY startTime DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getSessionsForDate(@org.jetbrains.annotations.NotNull
    java.lang.String date, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.screentime.guardian.data.local.AppSessionEntity>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertSession(@org.jetbrains.annotations.NotNull
    com.screentime.guardian.data.local.AppSessionEntity session, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE app_sessions SET endTime = :endTime, durationSeconds = :duration WHERE id = :sessionId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateSessionEnd(@org.jetbrains.annotations.NotNull
    java.lang.String sessionId, long endTime, long duration, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}