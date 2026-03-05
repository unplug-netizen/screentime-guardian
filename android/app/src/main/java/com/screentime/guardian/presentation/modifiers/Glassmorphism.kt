package com.screentime.guardian.presentation.modifiers

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Glassmorphism Modifier
 * Implementiert die Design-System Spezifikation:
 * - Background: rgba(255, 255, 255, 0.07)
 * - Backdrop-Blur: 20px (Android 12+)
 * - Border: 1px rgba(255, 255, 255, 0.10)
 * - Border-Radius: 16px
 * - Shadow: 0 8px 32px rgba(0, 0, 0, 0.12)
 */
fun Modifier.glassmorphism(): Modifier = composed {
    val isDarkTheme = true // TODO: Check actual theme
    
    this
        .shadow(
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp),
            ambientColor = Color.Black.copy(alpha = 0.12f),
            spotColor = Color.Black.copy(alpha = 0.12f)
        )
        .background(
            color = if (isDarkTheme) Color.White.copy(alpha = 0.07f)
                    else Color.White.copy(alpha = 0.70f),
            shape = RoundedCornerShape(16.dp)
        )
        .border(
            width = 1.dp,
            color = if (isDarkTheme) Color.White.copy(alpha = 0.10f)
                    else Color.Black.copy(alpha = 0.08f),
            shape = RoundedCornerShape(16.dp)
        )
        .then(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                Modifier.blur(radius = 20.dp)
            } else Modifier
        )
}

/**
 * Hervorgehobene Glass-Variante (für aktive Elemente)
 */
fun Modifier.glassmorphismHighlight(): Modifier = composed {
    val isDarkTheme = true
    
    this
        .shadow(
            elevation = 12.dp,
            shape = RoundedCornerShape(16.dp),
            ambientColor = Color.Black.copy(alpha = 0.15f),
            spotColor = Color.Black.copy(alpha = 0.15f)
        )
        .background(
            color = if (isDarkTheme) Color.White.copy(alpha = 0.15f)
                    else Color.White.copy(alpha = 0.90f),
            shape = RoundedCornerShape(16.dp)
        )
        .border(
            width = 1.dp,
            color = if (isDarkTheme) Color.White.copy(alpha = 0.20f)
                    else Color.Black.copy(alpha = 0.10f),
            shape = RoundedCornerShape(16.dp)
        )
}
