package com.screentime.guardian.subscription;

/**
 * Subscription & Feature-Gating
 * - RevenueCat Integration
 * - Feature-Flags für Free/Premium
 * - Paywall-Logik
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \"2\u00020\u0001:\u0001\"B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\r0\u000fJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J8\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\r0\u001b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\r0\u000fJ\b\u0010\u001e\u001a\u00020\rH\u0002J(\u0010\u001f\u001a\u00020\r2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\r0\u001b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\r0\u000fJ\u0006\u0010 \u001a\u00020\rJ\u0006\u0010!\u001a\u00020\rR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006#"}, d2 = {"Lcom/screentime/guardian/subscription/SubscriptionManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_subscriptionState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/screentime/guardian/subscription/SubscriptionState;", "subscriptionState", "Lkotlinx/coroutines/flow/StateFlow;", "getSubscriptionState", "()Lkotlinx/coroutines/flow/StateFlow;", "getOfferings", "", "callback", "Lkotlin/Function1;", "error/NonExistentClass", "isFeatureAvailable", "", "feature", "Lcom/screentime/guardian/subscription/PremiumFeature;", "purchasePackage", "activity", "Landroid/app/Activity;", "packageToPurchase", "Ljava/lang/Package;", "onSuccess", "Lkotlin/Function0;", "onError", "", "refreshSubscriptionStatus", "restorePurchases", "trackChallengeCreated", "trackLimitAdded", "Companion", "app_debug"})
public final class SubscriptionManager {
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.screentime.guardian.subscription.SubscriptionState> _subscriptionState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.screentime.guardian.subscription.SubscriptionState> subscriptionState = null;
    public static final int MAX_LIMITS_FREE = 3;
    public static final int HISTORY_DAYS_FREE = 7;
    public static final int MAX_CHALLENGES_FREE = 1;
    @org.jetbrains.annotations.NotNull
    private static final java.util.List<java.lang.String> PREMIUM_FEATURES = null;
    @org.jetbrains.annotations.NotNull
    public static final com.screentime.guardian.subscription.SubscriptionManager.Companion Companion = null;
    
    public SubscriptionManager(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.screentime.guardian.subscription.SubscriptionState> getSubscriptionState() {
        return null;
    }
    
    /**
     * Prüft ob Feature verfügbar ist
     */
    public final boolean isFeatureAvailable(@org.jetbrains.annotations.NotNull
    com.screentime.guardian.subscription.PremiumFeature feature) {
        return false;
    }
    
    /**
     * Holt verfügbare Offerings für Paywall
     */
    public final void getOfferings(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super error.NonExistentClass, kotlin.Unit> callback) {
    }
    
    /**
     * Kauft Premium-Abonnement
     */
    public final void purchasePackage(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.NotNull
    java.lang.Package packageToPurchase, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onError) {
    }
    
    /**
     * Stellt Kauf wieder her
     */
    public final void restorePurchases(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onError) {
    }
    
    /**
     * Aktualisiert Subscription-Status
     */
    private final void refreshSubscriptionStatus() {
    }
    
    /**
     * Trackt Usage für Limits
     */
    public final void trackLimitAdded() {
    }
    
    public final void trackChallengeCreated() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/screentime/guardian/subscription/SubscriptionManager$Companion;", "", "()V", "HISTORY_DAYS_FREE", "", "MAX_CHALLENGES_FREE", "MAX_LIMITS_FREE", "PREMIUM_FEATURES", "", "", "getPREMIUM_FEATURES", "()Ljava/util/List;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.List<java.lang.String> getPREMIUM_FEATURES() {
            return null;
        }
    }
}