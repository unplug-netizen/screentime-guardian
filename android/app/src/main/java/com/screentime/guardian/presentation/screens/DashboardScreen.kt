package com.screentime.guardian.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.screentime.guardian.presentation.components.*
import com.screentime.guardian.presentation.theme.*

/**
 * Dashboard Screen
 * Zeigt tägliche Statistiken, Progress Ring, App-Nutzung
 */
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackgroundPrimary)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { Spacer(modifier = Modifier.height(24.dp)) }

        // Circular Progress Ring
        item {
            CircularProgressRing(
                progress = uiState.dailyProgress,
                currentTime = uiState.currentScreenTime,
                goalTime = "von ${uiState.dailyGoalTime}",
                remainingTime = uiState.remainingTime,
                size = 280f
            )
        }

        item { Spacer(modifier = Modifier.height(32.dp)) }

        // Stat Cards Row
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Unlock Count Card
                GlassCard(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(
                                    color = GlassSurfaceElevated,
                                    shape = RoundedCornerShape(12.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null,
                                tint = AccentPrimary,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "${uiState.unlockCount}",
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontFamily = RobotoMono,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = TextPrimary
                            )
                            Text(
                                text = "Entsperrungen",
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextSecondary
                            )
                        }
                    }
                }

                // Goal Achievement Card
                GlassCard(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(
                                    color = GlassSurfaceElevated,
                                    shape = RoundedCornerShape(12.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.RadioButtonChecked,
                                contentDescription = null,
                                tint = AccentPrimary,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "${uiState.goalAchievement}%",
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontFamily = RobotoMono,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = TextPrimary
                            )
                            Text(
                                text = "Ziel\nerreicht",
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextSecondary
                            )
                        }
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(32.dp)) }

        // App Usage Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "App-Nutzung",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontFamily = PlayfairDisplay,
                        fontWeight = FontWeight.Medium
                    ),
                    color = TextPrimary
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { /* Navigate to details */ }
                ) {
                    Text(
                        text = "Details",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = AccentPrimary
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = AccentPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        // App Usage List
        items(uiState.appUsageList) { appUsage ->
            val (color, initial) = when (appUsage.category) {
                AppCategory.SOCIAL -> AppSocial to appUsage.appName.take(1)
                AppCategory.PRODUCTIVITY -> AppMessaging to appUsage.appName.take(1)
                AppCategory.ENTERTAINMENT -> AppVideo to appUsage.appName.take(1)
                else -> AppOther to appUsage.appName.take(1)
            }

            AppUsageItem(
                appName = appUsage.appName,
                appInitial = initial,
                usageTime = appUsage.formattedTime,
                progress = appUsage.progress,
                progressColor = color
            )
        }

        item { Spacer(modifier = Modifier.height(100.dp)) }
    }
}

// ViewModel
class DashboardViewModel : androidx.lifecycle.ViewModel() {
    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        // Demo data
        _uiState.value = DashboardUiState(
            dailyProgress = 0.875f,
            currentScreenTime = "3h 30m",
            dailyGoalTime = "4h 0m",
            remainingTime = "30m verbleibend",
            unlockCount = 47,
            goalAchievement = 88,
            appUsageList = listOf(
                AppUsageItem(
                    appName = "Instagram",
                    category = AppCategory.SOCIAL,
                    formattedTime = "1h 0m",
                    progress = 0.65f
                ),
                AppUsageItem(
                    appName = "WhatsApp",
                    category = AppCategory.PRODUCTIVITY,
                    formattedTime = "45m",
                    progress = 0.45f
                ),
                AppUsageItem(
                    appName = "YouTube",
                    category = AppCategory.ENTERTAINMENT,
                    formattedTime = "40m",
                    progress = 0.40f
                )
            )
        )
    }
}

data class DashboardUiState(
    val dailyProgress: Float = 0f,
    val currentScreenTime: String = "",
    val dailyGoalTime: String = "",
    val remainingTime: String = "",
    val unlockCount: Int = 0,
    val goalAchievement: Int = 0,
    val appUsageList: List<AppUsageItem> = emptyList()
)

data class AppUsageItem(
    val appName: String,
    val category: AppCategory,
    val formattedTime: String,
    val progress: Float
)

enum class AppCategory {
    SOCIAL, MESSAGING, VIDEO, PRODUCTIVITY, OTHER
}
