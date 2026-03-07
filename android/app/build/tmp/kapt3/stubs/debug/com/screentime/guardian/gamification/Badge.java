package com.screentime.guardian.gamification;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u001e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0002\u0010\u0011J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0007H\u00c6\u0003J\t\u0010%\u001a\u00020\tH\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\'\u001a\u00020\fH\u00c6\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001fJ\t\u0010)\u001a\u00020\u0010H\u00c6\u0003Jj\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u00c6\u0001\u00a2\u0006\u0002\u0010+J\u0013\u0010,\u001a\u00020\f2\b\u0010-\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010.\u001a\u00020/H\u00d6\u0001J\t\u00100\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0015\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001f\u00a8\u00061"}, d2 = {"Lcom/screentime/guardian/gamification/Badge;", "", "id", "", "name", "description", "tier", "Lcom/screentime/guardian/gamification/BadgeTier;", "criteria", "Lcom/screentime/guardian/gamification/BadgeCriteria;", "iconAsset", "isUnlocked", "", "unlockedAt", "", "progress", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/screentime/guardian/gamification/BadgeTier;Lcom/screentime/guardian/gamification/BadgeCriteria;Ljava/lang/String;ZLjava/lang/Long;F)V", "getCriteria", "()Lcom/screentime/guardian/gamification/BadgeCriteria;", "getDescription", "()Ljava/lang/String;", "getIconAsset", "getId", "()Z", "getName", "getProgress", "()F", "getTier", "()Lcom/screentime/guardian/gamification/BadgeTier;", "getUnlockedAt", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/screentime/guardian/gamification/BadgeTier;Lcom/screentime/guardian/gamification/BadgeCriteria;Ljava/lang/String;ZLjava/lang/Long;F)Lcom/screentime/guardian/gamification/Badge;", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class Badge {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String description = null;
    @org.jetbrains.annotations.NotNull
    private final com.screentime.guardian.gamification.BadgeTier tier = null;
    @org.jetbrains.annotations.NotNull
    private final com.screentime.guardian.gamification.BadgeCriteria criteria = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String iconAsset = null;
    private final boolean isUnlocked = false;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long unlockedAt = null;
    private final float progress = 0.0F;
    
    public Badge(@org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String description, @org.jetbrains.annotations.NotNull
    com.screentime.guardian.gamification.BadgeTier tier, @org.jetbrains.annotations.NotNull
    com.screentime.guardian.gamification.BadgeCriteria criteria, @org.jetbrains.annotations.NotNull
    java.lang.String iconAsset, boolean isUnlocked, @org.jetbrains.annotations.Nullable
    java.lang.Long unlockedAt, float progress) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.screentime.guardian.gamification.BadgeTier getTier() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.screentime.guardian.gamification.BadgeCriteria getCriteria() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getIconAsset() {
        return null;
    }
    
    public final boolean isUnlocked() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getUnlockedAt() {
        return null;
    }
    
    public final float getProgress() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.screentime.guardian.gamification.BadgeTier component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.screentime.guardian.gamification.BadgeCriteria component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component6() {
        return null;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component8() {
        return null;
    }
    
    public final float component9() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.screentime.guardian.gamification.Badge copy(@org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String description, @org.jetbrains.annotations.NotNull
    com.screentime.guardian.gamification.BadgeTier tier, @org.jetbrains.annotations.NotNull
    com.screentime.guardian.gamification.BadgeCriteria criteria, @org.jetbrains.annotations.NotNull
    java.lang.String iconAsset, boolean isUnlocked, @org.jetbrains.annotations.Nullable
    java.lang.Long unlockedAt, float progress) {
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