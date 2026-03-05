package com.screentime.guardian.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.screentime.guardian.R

// Colors - Dark Mode (Primary)
val DarkBackgroundPrimary = Color(0xFF0A0F1C)
val DarkBackgroundSecondary = Color(0xFF12182B)
val DarkBackgroundTertiary = Color(0xFF1A2340)
val DarkBackgroundElevated = Color(0xFF1E2A4A)

val GlassSurface = Color(0x0DFFFFFF)  // 5% opacity
val GlassSurfaceElevated = Color(0x14FFFFFF)  // 8%
val GlassBorder = Color(0x1AFFFFFF)   // 10%
val GlassBorderStrong = Color(0x26FFFFFF)   // 15%
val GlassHighlight = Color(0x33FFFFFF)  // 20%

val AccentPrimary = Color(0xFF34D399)   // Türkis/Grün
val AccentSecondary = Color(0xFF10B981) // Dunkleres Grün
val AccentGlow = Color(0x4D34D399)     // 30% opacity

val TextPrimary = Color(0xFFFFFFFF)
val TextSecondary = Color(0xB3FFFFFF)   // 70%
val TextTertiary = Color(0x80FFFFFF)    // 50%
val TextMuted = Color(0x59FFFFFF)       // 35%

// App Category Colors
val AppSocial = Color(0xFFEC4899)       // Pink (Instagram)
val AppMessaging = Color(0xFF10B981)    // Grün (WhatsApp)
val AppVideo = Color(0xFFEF4444)        // Rot (YouTube)
val AppProductivity = Color(0xFF3B82F6) // Blau
val AppOther = Color(0xFF6B7280)        // Grau

val Success = Color(0xFF34D399)
val Warning = Color(0xFFFBBF24)
val Error = Color(0xFFEF4444)

// Typography
val PlayfairDisplay = FontFamily(
    Font(R.font.playfair_display_regular, FontWeight.Normal),
    Font(R.font.playfair_display_medium, FontWeight.Medium),
    Font(R.font.playfair_display_semibold, FontWeight.SemiBold),
    Font(R.font.playfair_display_bold, FontWeight.Bold)
)

val RobotoMono = FontFamily(
    Font(R.font.roboto_mono_regular, FontWeight.Normal),
    Font(R.font.roboto_mono_medium, FontWeight.Medium),
    Font(R.font.roboto_mono_bold, FontWeight.Bold)
)
