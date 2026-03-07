package com.screentime.guardian.domain;

@kotlinx.serialization.Serializable
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\r\b\u0087\u0081\u0002\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f\u00a8\u0006\u000e"}, d2 = {"Lcom/screentime/guardian/domain/AppCategory;", "", "(Ljava/lang/String;I)V", "GAMES", "SOCIAL", "PRODUCTIVITY", "ENTERTAINMENT", "EDUCATION", "HEALTH", "NEWS", "SHOPPING", "FINANCE", "OTHER", "Companion", "app_debug"})
public enum AppCategory {
    /*public static final*/ GAMES /* = new GAMES() */,
    /*public static final*/ SOCIAL /* = new SOCIAL() */,
    /*public static final*/ PRODUCTIVITY /* = new PRODUCTIVITY() */,
    /*public static final*/ ENTERTAINMENT /* = new ENTERTAINMENT() */,
    /*public static final*/ EDUCATION /* = new EDUCATION() */,
    /*public static final*/ HEALTH /* = new HEALTH() */,
    /*public static final*/ NEWS /* = new NEWS() */,
    /*public static final*/ SHOPPING /* = new SHOPPING() */,
    /*public static final*/ FINANCE /* = new FINANCE() */,
    /*public static final*/ OTHER /* = new OTHER() */;
    @org.jetbrains.annotations.NotNull
    public static final com.screentime.guardian.domain.AppCategory.Companion Companion = null;
    
    AppCategory() {
    }
    
    @org.jetbrains.annotations.NotNull
    public static kotlin.enums.EnumEntries<com.screentime.guardian.domain.AppCategory> getEntries() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bH\u00c6\u0001\u00a8\u0006\t"}, d2 = {"Lcom/screentime/guardian/domain/AppCategory$Companion;", "", "()V", "fromSystemCategory", "Lcom/screentime/guardian/domain/AppCategory;", "category", "", "serializer", "Lkotlinx/serialization/KSerializer;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.screentime.guardian.domain.AppCategory fromSystemCategory(int category) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final kotlinx.serialization.KSerializer<com.screentime.guardian.domain.AppCategory> serializer() {
            return null;
        }
    }
}