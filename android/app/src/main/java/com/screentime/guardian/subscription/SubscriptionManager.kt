package com.screentime.guardian.subscription

import android.app.Activity
import android.content.Context
import com.revenuecat.purchases.*
import com.revenuecat.purchases.interfaces.ReceiveOfferingsCallback
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Subscription & Feature-Gating
 * - RevenueCat Integration
 * - Feature-Flags für Free/Premium
 * - Paywall-Logik
 */
class SubscriptionManager(
    context: Context
) {
    private val _subscriptionState = MutableStateFlow(SubscriptionState())
    val subscriptionState: StateFlow<SubscriptionState> = _subscriptionState
    
    companion object {
        // Feature-Flags für Free-Version
        const val MAX_LIMITS_FREE = 3
        const val HISTORY_DAYS_FREE = 7
        const val MAX_CHALLENGES_FREE = 1
        
        // Premium Features
        val PREMIUM_FEATURES = listOf(
            "unlimited_limits",
            "unlimited_history",
            "unlimited_challenges",
            "advanced_stats",
            "custom_themes",
            "export_data"
        )
    }
    
    init {
        // RevenueCat konfigurieren
        Purchases.configure(
            PurchasesConfiguration.Builder(
                context,
                "goog_XXXXXXXXXXXXXXXXXXXX" // TODO: Replace with actual RevenueCat API key
            ).build()
        )
        
        // Initialen Status laden
        refreshSubscriptionStatus()
    }
    
    /**
     * Prüft ob Feature verfügbar ist
     */
    fun isFeatureAvailable(feature: PremiumFeature): Boolean {
        return when (feature) {
            PremiumFeature.APP_LIMITS -> 
                _subscriptionState.value.isPremium || 
                _subscriptionState.value.limitsUsed < MAX_LIMITS_FREE
            PremiumFeature.HISTORY -> 
                _subscriptionState.value.isPremium
            PremiumFeature.CHALLENGES -> 
                _subscriptionState.value.isPremium || 
                _subscriptionState.value.challengesUsed < MAX_CHALLENGES_FREE
            PremiumFeature.ADVANCED_STATS -> 
                _subscriptionState.value.isPremium
            PremiumFeature.CUSTOM_THEMES -> 
                _subscriptionState.value.isPremium
            PremiumFeature.EXPORT_DATA -> 
                _subscriptionState.value.isPremium
        }
    }
    
    /**
     * Holt verfügbare Offerings für Paywall
     */
    fun getOfferings(callback: (Offerings?) -> Unit) {
        Purchases.sharedInstance.getOfferings(
            object : ReceiveOfferingsCallback {
                override fun onReceived(offerings: Offerings) {
                    callback(offerings)
                }
                
                override fun onError(error: PurchasesError) {
                    callback(null)
                }
            }
        )
    }
    
    /**
     * Kauft Premium-Abonnement
     */
    fun purchasePackage(
        activity: Activity,
        packageToPurchase: Package,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        Purchases.sharedInstance.purchase(
            PurchaseParams.Builder(activity, packageToPurchase).build(),
            object : PurchaseCallback {
                override fun onCompleted(storeTransaction: StoreTransaction, customerInfo: CustomerInfo) {
                    refreshSubscriptionStatus()
                    onSuccess()
                }
                
                override fun onError(error: PurchasesError, userCancelled: Boolean) {
                    if (userCancelled) {
                        onError("Purchase cancelled")
                    } else {
                        onError(error.message)
                    }
                }
            }
        )
    }
    
    /**
     * Stellt Kauf wieder her
     */
    fun restorePurchases(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        Purchases.sharedInstance.restorePurchases(
            object : ReceiveCustomerInfoCallback {
                override fun onReceived(customerInfo: CustomerInfo) {
                    refreshSubscriptionStatus()
                    onSuccess()
                }
                
                override fun onError(error: PurchasesError) {
                    onError(error.message)
                }
            }
        )
    }
    
    /**
     * Aktualisiert Subscription-Status
     */
    private fun refreshSubscriptionStatus() {
        Purchases.sharedInstance.getCustomerInfo(
            object : ReceiveCustomerInfoCallback {
                override fun onReceived(customerInfo: CustomerInfo) {
                    val isPremium = customerInfo.entitlements["premium"]?.isActive == true
                    _subscriptionState.value = _subscriptionState.value.copy(
                        isPremium = isPremium,
                        expirationDate = customerInfo.entitlements["premium"]?.expirationDate
                    )
                }
                
                override fun onError(error: PurchasesError) {
                    // Behalte aktuellen Status bei
                }
            }
        )
    }
    
    /**
     * Trackt Usage für Limits
     */
    fun trackLimitAdded() {
        _subscriptionState.value = _subscriptionState.value.copy(
            limitsUsed = _subscriptionState.value.limitsUsed + 1
        )
    }
    
    fun trackChallengeCreated() {
        _subscriptionState.value = _subscriptionState.value.copy(
            challengesUsed = _subscriptionState.value.challengesUsed + 1
        )
    }
}

// Data Classes

data class SubscriptionState(
    val isPremium: Boolean = false,
    val limitsUsed: Int = 0,
    val challengesUsed: Int = 0,
    val expirationDate: java.util.Date? = null
)

enum class PremiumFeature {
    APP_LIMITS,
    HISTORY,
    CHALLENGES,
    ADVANCED_STATS,
    CUSTOM_THEMES,
    EXPORT_DATA
}
