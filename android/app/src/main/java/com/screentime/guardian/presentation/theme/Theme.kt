package com.screentime.guardian.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.screentime.guardian.R

// Design Tokens - Dark Mode (Primary)
private val DarkBackgroundPrimary = Color(0xFF0A0F1C)
private val DarkBackgroundSecondary = Color(0xFF12182B)
private val DarkBackgroundTertiary = Color(0xFF1A2340)

private val GlassSurface = Color(0x12FFFFFF)  // 7% opacity
private val GlassBorder = Color(0x1AFFFFFF)   // 10% opacity
private val GlassHighlight = Color(0x26FFFFFF) // 15% opacity

private val AccentPrimary = Color(0xFF34D399)   // Emerald 400
private val AccentSecondary = Color(0xFF10B981) // Emerald 500
private val AccentTertiary = Color(0xFF6EE7B7)  // Emerald 300

private val TextPrimary = Color(0xFFFAFAFA)
private val TextSecondary = Color(0xB3FAFAFA)   // 70% opacity
private val TextTertiary = Color(0x80FAFAFA)    // 50% opacity

// Light Mode
private val LightBackgroundPrimary = Color(0xFFFAFAFA)
private val LightBackgroundSecondary = Color(0xFFF3F4F6)
private val LightBackgroundTertiary = Color(0xFFE5E7EB)

private val LightGlassSurface = Color(0xB3FFFFFF)  // 70% opacity
private val LightGlassBorder = Color(0x14000000)   // 8% opacity
private val LightGlassHighlight = Color(0xE6FFFFFF) // 90% opacity

private val LightTextPrimary = Color(0xFF0A0F1C)
private val LightTextSecondary = Color(0xB30A0F1C)  // 70% opacity
private val LightTextTertiary = Color(0x800A0F1C)   // 50% opacity

// Typography
val PlayfairDisplay = FontFamily(
    Font(R.font.playfair_display_regular, FontWeight.Normal),
    Font(R.font.playfair_display_medium, FontWeight.Medium),
    Font(R.font.playfair_display_semibold, FontWeight.SemiBold),
    Font(R.font.playfair_display_bold, FontWeight.Bold)
)

val RobotoMono = FontFamily(
    Font(R.font.roboto_mono_regular, FontWeight.Normal),
    Font(R.font.roboto_mono_medium, FontWeight.Medium)
)

// Dark Color Scheme
private val DarkColorScheme = darkColorScheme(
    primary = AccentPrimary,
    secondary = AccentSecondary,
    tertiary = AccentTertiary,
    background = DarkBackgroundPrimary,
    surface = DarkBackgroundSecondary,
    surfaceVariant = DarkBackgroundTertiary,
    onPrimary = DarkBackgroundPrimary,
    onSecondary = DarkBackgroundPrimary,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onSurfaceVariant = TextSecondary,
    outline = GlassBorder,
    error = Color(0xFFF87171),
    onError = Color.White
)

// Light Color Scheme
private val LightColorScheme = lightColorScheme(
    primary = AccentSecondary,
    secondary = AccentPrimary,
    tertiary = AccentTertiary,
    background = LightBackgroundPrimary,
    surface = LightBackgroundSecondary,
    surfaceVariant = LightBackgroundTertiary,
    onPrimary = Color.White,
    onSecondary = LightBackgroundPrimary,
    onBackground = LightTextPrimary,
    onSurface = LightTextPrimary,
    onSurfaceVariant = LightTextSecondary,
    outline = LightGlassBorder,
    error = Color(0xFFDC2626),
    onError = Color.White
)

// Typography Scale
private val AppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = (-0.5).sp
    ),
    displayMedium = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.3).sp
    ),
    displaySmall = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.2).sp
    ),
    headlineLarge = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.3).sp
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
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
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
    darkTheme: Boolean = true, // Default to dark for glassmorphism
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
