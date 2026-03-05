package com.screentime.guardian.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.screentime.guardian.presentation.theme.*

/**
 * Glass Card Component
 * Background: 5% opacity white
 * Border: 1px 10% white
 * Border-Radius: 16px
 */
@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "card_scale"
    )

    Box(
        modifier = modifier
            .scale(scale)
            .background(
                color = GlassSurface,
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                color = GlassBorder,
                shape = RoundedCornerShape(16.dp)
            )
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onClick
                    )
                } else Modifier
            ),
        content = content
    )
}

/**
 * Primary Action Button (Filled Pill)
 * Background: #34D399
 * Height: 56dp
 * Border-Radius: 28dp
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
                color = if (enabled) AccentPrimary else AccentPrimary.copy(alpha = 0.3f),
                shape = RoundedCornerShape(28.dp)
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
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                tint = DarkBackgroundPrimary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = DarkBackgroundPrimary
            )
        }
    }
}

/**
 * Duration Selector Pills
 */
@Composable
fun DurationSelector(
    durations: List<Int>,
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

            val backgroundColor = if (isSelected) AccentPrimary else GlassSurface
            val textColor = if (isSelected) DarkBackgroundPrimary else TextSecondary
            val borderWidth = if (isSelected) 0.dp else 1.dp

            Box(
                modifier = Modifier
                    .height(44.dp)
                    .defaultMinSize(minWidth = 64.dp)
                    .background(
                        color = backgroundColor,
                        shape = CircleShape
                    )
                    .border(
                        width = borderWidth,
                        color = GlassBorder,
                        shape = CircleShape
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
 * Toggle Setting Row
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
                    color = AccentPrimary.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(12.dp)
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
                color = TextPrimary
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = TextTertiary
            )
        }

        // Custom Toggle Switch
        val toggleBackground = if (isEnabled) AccentPrimary else GlassSurfaceElevated
        val thumbOffset = if (isEnabled) 20.dp else 2.dp

        Box(
            modifier = Modifier
                .width(52.dp)
                .height(32.dp)
                .background(
                    color = toggleBackground,
                    shape = CircleShape
                )
                .clickable { onToggle(!isEnabled) }
                .padding(2.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .offset(x = thumbOffset)
                    .size(28.dp)
                    .background(
                        color = Color.White,
                        shape = CircleShape
                    )
            )
        }
    }
}
