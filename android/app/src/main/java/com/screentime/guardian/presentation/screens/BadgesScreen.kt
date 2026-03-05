package com.screentime.guardian.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.screentime.guardian.presentation.components.GlassCard
import com.screentime.guardian.presentation.theme.*
import com.screentime.guardian.subscription.PremiumFeature
import com.screentime.guardian.subscription.SubscriptionManager

/**
 * Badges Screen
 * Zeigt alle Badges in 3D-Stack-Visualisierung
 * Monochrome bis freigeschaltet
 */
@Composable
fun BadgesScreen(
    subscriptionManager: SubscriptionManager = remember { SubscriptionManager(androidx.compose.ui.platform.LocalContext.current) }
) {
    val badges = remember {
        listOf(
            BadgeDisplay("First Steps", "Complete your first focus session", BadgeTier.BRONZE, true),
            BadgeDisplay("Early Bird", "Complete 3 focus sessions before 12:00 PM", BadgeTier.BRONZE, true),
            BadgeDisplay("Week Warrior", "7 consecutive days meeting 6+ hour focus goal", BadgeTier.SILVER, true),
            BadgeDisplay("Deep Diver", "Sustained single session exceeding 2 hours", BadgeTier.SILVER, true),
            BadgeDisplay("Distraction Denier", "Keep distracting app usage under 10% for 7 days", BadgeTier.SILVER, false),
            BadgeDisplay("Month Master", "30 consecutive days of 6+ hour focus", BadgeTier.GOLD, false),
            BadgeDisplay("Focus Champion", "Accumulate 100 hours of focus time", BadgeTier.GOLD, false),
            BadgeDisplay("Centurion", "100 consecutive days meeting your goals", BadgeTier.PLATINUM, false),
            BadgeDisplay("Marathon", "Complete a 4-hour focus session", BadgeTier.SPECIAL, false)
        )
    }
    
    val unlockedCount = badges.count { it.isUnlocked }
    val totalCount = badges.size
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackgroundPrimary)
            .padding(horizontal = 20.dp)
    ) {
        item { Spacer(modifier = Modifier.height(24.dp)) }
        
        // Title
        item {
            Text(
                text = "Badges",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontFamily = PlayfairDisplay,
                    fontWeight = FontWeight.Medium
                ),
                color = TextPrimary
            )
        }
        
        item { Spacer(modifier = Modifier.height(8.dp)) }
        
        // Subtitle
        item {
            Text(
                text = "$unlockedCount von $totalCount freigeschaltet",
                style = MaterialTheme.typography.bodyLarge,
                color = TextSecondary
            )
        }
        
        item { Spacer(modifier = Modifier.height(24.dp)) }
        
        // Badge Categories
        BadgeTier.values().forEach { tier ->
            val tierBadges = badges.filter { it.tier == tier }
            
            if (tierBadges.isNotEmpty()) {
                item {
                    Text(
                        text = when (tier) {
                            BadgeTier.BRONZE -> "Bronze"
                            BadgeTier.SILVER -> "Silver"
                            BadgeTier.GOLD -> "Gold"
                            BadgeTier.PLATINUM -> "Platinum"
                            BadgeTier.SPECIAL -> "Special"
                        },
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = when (tier) {
                            BadgeTier.BRONZE -> Color(0xFFCD7F32)
                            BadgeTier.SILVER -> Color(0xFFC0C0C0)
                            BadgeTier.GOLD -> Color(0xFFFFD700)
                            BadgeTier.PLATINUM -> Color(0xFFE5E4E2)
                            BadgeTier.SPECIAL -> AccentPrimary
                        },
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }
                
                items(tierBadges) { badge ->
                    BadgeCard(badge = badge)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
        
        item { Spacer(modifier = Modifier.height(100.dp)) }
    }
}

@Composable
private fun BadgeCard(badge: BadgeDisplay) {
    val alpha = if (badge.isUnlocked) 1f else 0.4f
    val grayscale = if (badge.isUnlocked) 0f else 1f
    
    GlassCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Badge Icon
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        color = when (badge.tier) {
                            BadgeTier.BRONZE -> Color(0xFFCD7F32).copy(alpha = 0.15f)
                            BadgeTier.SILVER -> Color(0xFFC0C0C0).copy(alpha = 0.15f)
                            BadgeTier.GOLD -> Color(0xFFFFD700).copy(alpha = 0.15f)
                            BadgeTier.PLATINUM -> Color(0xFFE5E4E2).copy(alpha = 0.15f)
                            BadgeTier.SPECIAL -> AccentPrimary.copy(alpha = 0.15f)
                        },
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (badge.isUnlocked) {
                    Text(
                        text = "🏆",
                        style = MaterialTheme.typography.headlineSmall
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = TextTertiary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Content
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = badge.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = TextPrimary.copy(alpha = alpha)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = badge.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary.copy(alpha = alpha)
                )
            }
        }
    }
}

data class BadgeDisplay(
    val name: String,
    val description: String,
    val tier: BadgeTier,
    val isUnlocked: Boolean
)

enum class BadgeTier {
    BRONZE, SILVER, GOLD, PLATINUM, SPECIAL
}
