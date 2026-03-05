package com.screentime.guardian.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.screentime.guardian.presentation.components.GlassCard
import com.screentime.guardian.presentation.theme.*
import com.screentime.guardian.subscription.PremiumFeature
import com.screentime.guardian.subscription.SubscriptionManager

/**
 * Paywall Screen (Glassmorphism Modal)
 * Monatlich/Jährlich Toggle, Feature-Liste, CTA
 */
@Composable
fun PaywallScreen(
    subscriptionManager: SubscriptionManager,
    onDismiss: () -> Unit,
    onSubscribe: () -> Unit
) {
    var isYearly by remember { mutableStateOf(true) }
    
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = DarkBackgroundPrimary,
                    shape = RoundedCornerShape(24.dp)
                )
                .border(
                    width = 1.dp,
                    color = GlassBorder,
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Close Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = TextTertiary,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onDismiss() }
                    )
                }
                
                // Premium Icon
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(
                            color = AccentPrimary.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = AccentPrimary,
                        modifier = Modifier.size(32.dp)
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Title
                Text(
                    text = "Go Premium",
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontFamily = PlayfairDisplay,
                        fontWeight = FontWeight.Medium
                    ),
                    color = TextPrimary,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Subtitle
                Text(
                    text = "Entsperre alle Features\nund werde produktiver",
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextSecondary,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Billing Toggle
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = GlassSurface,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                color = if (!isYearly) AccentPrimary else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { isYearly = false }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Monatlich",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            color = if (!isYearly) DarkBackgroundPrimary else TextSecondary
                        )
                    }
                    
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                color = if (isYearly) AccentPrimary else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { isYearly = true }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Jährlich (-30%)",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            color = if (isYearly) DarkBackgroundPrimary else TextSecondary
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Price
                Text(
                    text = if (isYearly) "€29,99/Jahr" else "€4,99/Monat",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontFamily = RobotoMono,
                        fontWeight = FontWeight.Bold
                    ),
                    color = TextPrimary
                )
                
                if (isYearly) {
                    Text(
                        text = "€2,50/Monat",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextTertiary
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Feature List
                PremiumFeatureItem("Unbegrenzte App-Limits")
                PremiumFeatureItem("Unbegrenzte Challenges")
                PremiumFeatureItem("Erweiterte Statistiken")
                PremiumFeatureItem("Daten-Export")
                PremiumFeatureItem("Premium Themes")
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Subscribe Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(
                            color = AccentPrimary,
                            shape = RoundedCornerShape(28.dp)
                        )
                        .clickable { onSubscribe() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (isYearly) "Jährlich abonnieren" else "Monatlich abonnieren",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = DarkBackgroundPrimary
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Restore Purchases
                Text(
                    text = "Käufe wiederherstellen",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = AccentPrimary,
                    modifier = Modifier.clickable { 
                        subscriptionManager.restorePurchases(
                            onSuccess = { onDismiss() },
                            onError = { /* Show error */ }
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun PremiumFeatureItem(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = AccentPrimary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = TextPrimary
        )
    }
}
