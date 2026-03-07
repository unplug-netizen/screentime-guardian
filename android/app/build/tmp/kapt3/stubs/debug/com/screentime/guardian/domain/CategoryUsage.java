package com.screentime.guardian.domain;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J-\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lcom/screentime/guardian/domain/CategoryUsage;", "", "category", "Lcom/screentime/guardian/domain/AppCategory;", "totalSeconds", "", "topApps", "", "Lcom/screentime/guardian/domain/AppUsageBreakdown;", "(Lcom/screentime/guardian/domain/AppCategory;JLjava/util/List;)V", "getCategory", "()Lcom/screentime/guardian/domain/AppCategory;", "getTopApps", "()Ljava/util/List;", "getTotalSeconds", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
public final class CategoryUsage {
    @org.jetbrains.annotations.NotNull
    private final com.screentime.guardian.domain.AppCategory category = null;
    private final long totalSeconds = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.screentime.guardian.domain.AppUsageBreakdown> topApps = null;
    
    public CategoryUsage(@org.jetbrains.annotations.NotNull
    com.screentime.guardian.domain.AppCategory category, long totalSeconds, @org.jetbrains.annotations.NotNull
    java.util.List<com.screentime.guardian.domain.AppUsageBreakdown> topApps) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.screentime.guardian.domain.AppCategory getCategory() {
        return null;
    }
    
    public final long getTotalSeconds() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.screentime.guardian.domain.AppUsageBreakdown> getTopApps() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.screentime.guardian.domain.AppCategory component1() {
        return null;
    }
    
    public final long component2() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.screentime.guardian.domain.AppUsageBreakdown> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.screentime.guardian.domain.CategoryUsage copy(@org.jetbrains.annotations.NotNull
    com.screentime.guardian.domain.AppCategory category, long totalSeconds, @org.jetbrains.annotations.NotNull
    java.util.List<com.screentime.guardian.domain.AppUsageBreakdown> topApps) {
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