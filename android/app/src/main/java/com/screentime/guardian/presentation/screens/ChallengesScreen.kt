package com.screentime.guardian.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.screentime.guardian.presentation.components.GlassCard
import com.screentime.guardian.presentation.components.PrimaryActionButton
import com.screentime.guardian.presentation.theme.*

/**
 * Challenges Screen
 * Zeigt aktive Challenges, Challenge-Typen, Create Button
 */
@Composable
fun ChallengesScreen(
    onCreateChallenge: () -> Unit = {},
    onShare: () -> Unit = {},
    onChallengeClick: (Challenge) -> Unit = {}
) {
    val activeChallenges = remember {
        listOf(
            Challenge(
                id = "1",
                title = "Wochen-Challenge",
                description = "Wer schafft die meiste Focus-Time?",
                participants = 3,
                endDate = "Bis 12. März",
                icon = "trophy"
            )
        )
    }

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
                text = "Challenges",
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
                text = "Tritt gegen Freunde an",
                style = MaterialTheme.typography.bodyLarge,
                color = TextSecondary
            )
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }

        // Action Buttons Row
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // New Challenge Button
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                        .background(
                            color = AccentPrimary,
                            shape = RoundedCornerShape(28.dp)
                        )
                        .clickable(onClick = onCreateChallenge),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = DarkBackgroundPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Neue Challenge",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = DarkBackgroundPrimary
                        )
                    }
                }

                // Share Button
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(
                            color = GlassSurface,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = GlassBorder,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable(onClick = onShare),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        tint = TextPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        item { Spacer(modifier = Modifier.height(32.dp)) }

        // Active Challenges Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Aktive Challenges",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = TextPrimary
                )
                Text(
                    text = "${activeChallenges.size}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = RobotoMono,
                        fontWeight = FontWeight.Bold
                    ),
                    color = AccentPrimary
                )
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        // Active Challenges List
        items(activeChallenges) { challenge ->
            ChallengeCard(
                challenge = challenge,
                onClick = { onChallengeClick(challenge) }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }

        // Challenge Types Header
        item {
            Text(
                text = "Challenge-Typen",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = TextPrimary
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        // Challenge Types Grid (2 columns)
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ChallengeTypeCard(
                    icon = "timer",
                    title = "Focus Time",
                    description = "Wer fokussiert sich am längsten?",
                    color = AccentPrimary,
                    modifier = Modifier.weight(1f)
                )
                ChallengeTypeCard(
                    icon = "screen_time",
                    title = "Screen Time",
                    description = "Wer hat die wenigste Bildschirmzeit?",
                    color = AppSocial,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item { Spacer(modifier = Modifier.height(100.dp)) }
    }
}

@Composable
private fun ChallengeCard(
    challenge: Challenge,
    onClick: () -> Unit
) {
    GlassCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        color = AccentPrimary.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🏆",
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Content
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = challenge.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = challenge.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = TextMuted,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${challenge.participants} Teilnehmer",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        tint = TextMuted,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = challenge.endDate,
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted
                    )
                }
            }

            // Arrow
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = TextMuted,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
private fun ChallengeTypeCard(
    icon: String,
    title: String,
    description: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    GlassCard(modifier = modifier) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = color.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when (icon) {
                        "timer" -> "⏱️"
                        "screen_time" -> "📱"
                        else -> "🎯"
                    },
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = TextPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
        }
    }
}

data class Challenge(
    val id: String,
    val title: String,
    val description: String,
    val participants: Int,
    val endDate: String,
    val icon: String
)
