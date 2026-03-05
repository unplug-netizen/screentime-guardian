package com.screentime.guardian.presentation.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.screentime.guardian.presentation.modifiers.glassmorphism

/**
 * Glass Card Component
 * Background: 7% opacity white
 * Backdrop-Blur: 20px
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
            .glassmorphism()
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
 * Glass Button (Primary)
 * Background: 20% accent color
 * Border: 1px 30% accent
 */
@Composable
fun GlassButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable BoxScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "button_scale"
    )
    
    val accentColor = MaterialTheme.colorScheme.primary
    
    Box(
        modifier = modifier
            .scale(scale)
            .shadow(
                elevation = if (enabled) 8.dp else 0.dp,
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = if (enabled) accentColor.copy(alpha = 0.20f) 
                        else accentColor.copy(alpha = 0.05f),
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = if (enabled) accentColor.copy(alpha = 0.30f)
                        else accentColor.copy(alpha = 0.10f),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick
            )
            .padding(horizontal = 24.dp, vertical = 12.dp),
        content = content
    )
}

/**
 * Glass Input Field
 */
@Composable
fun GlassInput(
    modifier: Modifier = Modifier,
    isFocused: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    val accentColor = MaterialTheme.colorScheme.primary
    
    Box(
        modifier = modifier
            .background(
                color = Color.White.copy(alpha = 0.05f),
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = if (isFocused) accentColor.copy(alpha = 0.50f)
                        else Color.White.copy(alpha = 0.08f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        content = content
    )
}

/**
 * Stats Card für Dashboard
 * Zeigt eine einzelne Statistik mit Icon
 */
@Composable
fun StatCard(
    title: String,
    value: String,
    unit: String? = null,
    modifier: Modifier = Modifier,
    trend: Trend? = null
) {
    GlassCard(modifier = modifier) {
        // Implementation in separate file
    }
}

enum class Trend {
    UP, DOWN, STABLE
}
