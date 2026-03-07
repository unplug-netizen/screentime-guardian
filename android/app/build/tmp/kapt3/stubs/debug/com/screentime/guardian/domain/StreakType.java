package com.screentime.guardian.domain;

@kotlinx.serialization.Serializable
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0087\u0081\u0002\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0006B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0007"}, d2 = {"Lcom/screentime/guardian/domain/StreakType;", "", "(Ljava/lang/String;I)V", "DAILY_FOCUS", "DISTRACTION_FREE", "EARLY_START", "Companion", "app_debug"})
public enum StreakType {
    /*public static final*/ DAILY_FOCUS /* = new DAILY_FOCUS() */,
    /*public static final*/ DISTRACTION_FREE /* = new DISTRACTION_FREE() */,
    /*public static final*/ EARLY_START /* = new EARLY_START() */;
    @org.jetbrains.annotations.NotNull
    public static final com.screentime.guardian.domain.StreakType.Companion Companion = null;
    
    StreakType() {
    }
    
    @org.jetbrains.annotations.NotNull
    public static kotlin.enums.EnumEntries<com.screentime.guardian.domain.StreakType> getEntries() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00c6\u0001\u00a8\u0006\u0006"}, d2 = {"Lcom/screentime/guardian/domain/StreakType$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/screentime/guardian/domain/StreakType;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final kotlinx.serialization.KSerializer<com.screentime.guardian.domain.StreakType> serializer() {
            return null;
        }
    }
}