package com.screentime.guardian.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Dark Color Scheme
private val DarkColorScheme = darkColorScheme(
    primary = AccentPrimary,
    onPrimary = DarkBackgroundPrimary,
    secondary = AccentSecondary,
    onSecondary = DarkBackgroundPrimary,
    background = DarkBackgroundPrimary,
    onBackground = TextPrimary,
    surface = DarkBackgroundSecondary,
    onSurface = TextPrimary,
    surfaceVariant = DarkBackgroundTertiary,
    onSurfaceVariant = TextSecondary,
    outline = GlassBorder,
    error = Error,
    onError = Color.White
)

// Light Color Scheme (fallback)
private val LightColorScheme = lightColorScheme(
    primary = AccentSecondary,
    onPrimary = Color.White,
    secondary = AccentPrimary,
    onSecondary = DarkBackgroundPrimary,
    background = Color(0xFFFAFAFA),
    onBackground = DarkBackgroundPrimary,
    surface = Color(0xFFF3F4F6),
    onSurface = DarkBackgroundPrimary,
    surfaceVariant = Color(0xFFE5E7EB),
    onSurfaceVariant = Color(0xB30A0F1C),
    outline = Color(0x14000000),
    error = Color(0xFFDC2626),
    onError = Color.White
)

// Typography
private val AppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.Bold,
        fontSize = 64.sp,
        lineHeight = 72.sp,
        letterSpacing = (-2).sp
    ),
    displayMedium = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.SemiBold,
        fontSize = 40.sp,
        lineHeight = 48.sp,
        letterSpacing = (-0.5).sp
    ),
    displaySmall = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = (-0.3).sp
    ),
    headlineLarge = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.2).sp
    ),
    headlineMedium = TextStyle(
        fontFamily = RobotoMono,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = (-1).sp
    ),
    headlineSmall = TextStyle(
        fontFamily = RobotoMono,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.5).sp
    ),
    titleLarge = TextStyle(
        fontFamily = RobotoMono,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = RobotoMono,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = androidx.compose.ui.text.font.FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = androidx.compose.ui.text.font.FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = androidx.compose.ui.text.font.FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontFamily = RobotoMono,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = RobotoMono,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.2.sp
    ),
    labelSmall = TextStyle(
        fontFamily = androidx.compose.ui.text.font.FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

// Shapes
private val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp)
)

@Composable
fun ScreenTimeGuardianTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
