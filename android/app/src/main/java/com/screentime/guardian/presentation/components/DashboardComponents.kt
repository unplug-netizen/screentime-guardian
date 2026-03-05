package com.screentime.guardian.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.screentime.guardian.presentation.theme.RobotoMono
import kotlin.math.cos
import kotlin.math.sin

/**
 * Circular Progress Ring für Dashboard
 * Zeigt verbleibende Zeit / Ziel-Zeit an
 */
@Composable
fun CircularProgressRing(
    progress: Float, // 0.0f to 1.0f
    currentTime: String, // "3h 30m"
    goalTime: String, // "von 4h 0m"
    remainingTime: String, // "30m verbleibend"
    modifier: Modifier = Modifier,
    size: Float = 280f
) {
    val infiniteTransition = rememberInfiniteTransition(label = "glow")
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow_alpha"
    )

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "progress"
    )

    Box(
        modifier = modifier.size(size.dp),
        contentAlignment = Alignment.Center
    ) {
        // Glow-Effekt
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size / 2, size / 2)
            val radius = (size / 2) - 20f

            // Äußerer Glow
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF34D399).copy(alpha = glowAlpha * 0.3f),
                        Color.Transparent
                    ),
                    center = center,
                    radius = radius * 1.3f
                ),
                radius = radius * 1.3f,
                center = center
            )
        }

        // Progress Ring
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size / 2, size / 2)
            val radius = (size / 2) - 30f
            val strokeWidth = 18f

            // Hintergrund-Kreis
            drawArc(
                color = Color.White.copy(alpha = 0.05f),
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )

            // Progress-Bogen
            val sweepAngle = animatedProgress * 360f
            val gradientBrush = Brush.sweepGradient(
                colors = listOf(
                    Color(0xFF34D399),
                    Color(0xFF10B981),
                    Color(0xFF34D399)
                ),
                center = center
            )

            drawArc(
                brush = gradientBrush,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }

        // Center Content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currentTime,
                style = MaterialTheme.typography.displayLarge.copy(
                    fontFamily = RobotoMono,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
            Text(
                text = goalTime,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.60f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = remainingTime,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = Color(0xFF34D399)
            )
        }
    }
}

/**
 * Stat Card für Dashboard
 */
@Composable
fun StatCard(
    icon: @Composable () -> Unit,
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    GlassCard(modifier = modifier) {
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
                        color = Color.White.copy(alpha = 0.08f),
                        shape = androidx.compose.foundation.shape.CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                icon()
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = RobotoMono,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White
                )
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.60f)
                )
            }
        }
    }
}

/**
 * App Usage Item mit farbiger Progress-Bar
 */
@Composable
fun AppUsageItem(
    appName: String,
    appInitial: String,
    usageTime: String,
    progress: Float, // 0.0f to 1.0f
    progressColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // App Icon (Initial)
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(
                    color = progressColor.copy(alpha = 0.15f),
                    shape = androidx.compose.foundation.shape.CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = appInitial,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = progressColor
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // App Info
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = appName,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = Color.White
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Progress Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.08f),
                        shape = androidx.compose.foundation.shape.CircleShape
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .height(6.dp)
                        .background(
                            color = progressColor,
                            shape = androidx.compose.foundation.shape.CircleShape
                        )
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Usage Time
        Text(
            text = usageTime,
            style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = RobotoMono
            ),
            color = Color.White.copy(alpha = 0.80f)
        )
    }
}

/**
 * Duration Selector Pills (für Focus-Modus)
 */
@Composable
fun DurationSelector(
    durations: List<Int>, // in Minuten
    selectedDuration: Int,
    onDurationSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        durations.forEach { duration ->
            val isSelected = duration == selectedDuration

            val backgroundColor = if (isSelected) {
                Color(0xFF34D399)
            } else {
                Color.White.copy(alpha = 0.05f)
            }

            val textColor = if (isSelected) {
                Color(0xFF0A0F1C)
            } else {
                Color.White.copy(alpha = 0.70f)
            }

            Box(
                modifier = Modifier
                    .height(44.dp)
                    .defaultMinSize(minWidth = 64.dp)
                    .background(
                        color = backgroundColor,
                        shape = androidx.compose.foundation.shape.CircleShape
                    )
                    .border(
                        width = if (isSelected) 0.dp else 1.dp,
                        color = Color.White.copy(alpha = 0.10f),
                        shape = androidx.compose.foundation.shape.CircleShape
                    )
                    .clickable { onDurationSelected(duration) }
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${duration}m",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = RobotoMono,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    ),
                    color = textColor
                )
            }
        }
    }
}

/**
 * Primary Action Button mit Play-Icon
 */
@Composable
fun PrimaryActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "button_scale"
    )

    Box(
        modifier = modifier
            .scale(scale)
            .fillMaxWidth()
            .height(56.dp)
            .background(
                color = if (enabled) Color(0xFF34D399) else Color(0xFF34D399).copy(alpha = 0.3f),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(28.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.PlayArrow,
                contentDescription = null,
                tint = Color(0xFF0A0F1C),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color(0xFF0A0F1C)
            )
        }
    }
}

/**
 * Toggle Setting Row (für Focus-Einstellungen)
 */
@Composable
fun ToggleSettingRow(
    icon: @Composable () -> Unit,
    title: String,
    subtitle: String,
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = Color(0xFF34D399).copy(alpha = 0.15f),
                    shape = androidx.compose.foundation.shape.CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = Color.White
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.50f)
            )
        }

        // Toggle Switch (vereinfacht)
        val toggleBackground = if (isEnabled) Color(0xFF34D399) else Color.White.copy(alpha = 0.15f)
        val thumbOffset = if (isEnabled) 20.dp else 2.dp

        Box(
            modifier = Modifier
                .width(52.dp)
                .height(32.dp)
                .background(
                    color = toggleBackground,
                    shape = androidx.compose.foundation.shape.CircleShape
                )
                .clickable { onToggle(!isEnabled) },
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .offset(x = thumbOffset)
                    .size(28.dp)
                    .background(
                        color = Color.White,
                        shape = androidx.compose.foundation.shape.CircleShape
                    )
            )
        }
    }
}
