package com.screentime.guardian.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.screentime.guardian.presentation.components.*
import com.screentime.guardian.presentation.theme.*

/**
 * Focus Mode Screen
 * Timer, Duration Selection, Start Button, Settings
 */
@Composable
fun FocusModeScreen(
    onStartFocus: (Int) -> Unit = {}
) {
    var selectedDuration by remember { mutableStateOf(25) }
    var soundEnabled by remember { mutableStateOf(true) }
    var notificationsEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackgroundPrimary)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // Title
        Text(
            text = "Focus-Modus",
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
            text = "Bleib fokussiert und produktiv",
            style = MaterialTheme.typography.bodyLarge,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Timer Display
        Box(
            modifier = Modifier
                .size(280.dp)
                .background(
                    color = GlassSurface,
                    shape = RoundedCornerShape(140.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            // Timer Text
            Text(
                text = String.format("%02d:00", selectedDuration),
                style = MaterialTheme.typography.displayLarge.copy(
                    fontFamily = RobotoMono,
                    fontWeight = FontWeight.Bold,
                    fontSize = 64.sp
                ),
                color = TextPrimary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Minuten",
            style = MaterialTheme.typography.bodyLarge,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Duration Selector Label
        Text(
            text = "Dauer wählen",
            style = MaterialTheme.typography.bodyLarge,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Duration Pills
        DurationSelector(
            durations = listOf(15, 25, 45, 60),
            selectedDuration = selectedDuration,
            onDurationSelected = { selectedDuration = it }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Start Button
        PrimaryActionButton(
            text = "Starten",
            onClick = { onStartFocus(selectedDuration) }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Settings Card
        GlassCard {
            Column(modifier = Modifier.padding(20.dp)) {
                // Sound Setting
                ToggleSettingRow(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = null,
                            tint = AccentPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    title = "Sound",
                    subtitle = "Am Ende abspielen",
                    isEnabled = soundEnabled,
                    onToggle = { soundEnabled = it }
                )

                Divider(
                    color = GlassBorder,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Notifications Setting
                ToggleSettingRow(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = null,
                            tint = AccentPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    title = "Benachrichtigungen",
                    subtitle = "Bei Fokus-Ende",
                    isEnabled = notificationsEnabled,
                    onToggle = { notificationsEnabled = it }
                )
            }
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}
