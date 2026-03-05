package com.screentime.guardian.blocking

import android.content.Context
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.screentime.guardian.R
import com.screentime.guardian.presentation.components.GlassButton
import com.screentime.guardian.presentation.theme.ScreenTimeGuardianTheme

/**
 * Block-Screen mit Glassmorphism Design
 * Dark-Mode Support, keine Text-Eingabe, <3 Sekunden Entsperrung
 */
class BlockOverlayView(context: Context) : ComposeView(context) {
    
    fun setup(
        appName: String,
        limitMinutes: Int,
        onUnlock: (UnlockMethod) -> Unit
    ) {
        setContent {
            ScreenTimeGuardianTheme(darkTheme = true) {
                BlockScreen(
                    appName = appName,
                    limitMinutes = limitMinutes,
                    onBiometricUnlock = { onUnlock(UnlockMethod.BIOMETRIC) },
                    onPinUnlock = { onUnlock(UnlockMethod.PIN) }
                )
            }
        }
    }
}

@Composable
private fun BlockScreen(
    appName: String,
    limitMinutes: Int,
    onBiometricUnlock: () -> Unit,
    onPinUnlock: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.85f)),
        contentAlignment = Alignment.Center
    ) {
        // Glassmorphism Card
        Box(
            modifier = Modifier
                .padding(32.dp)
                .background(
                    color = Color.White.copy(alpha = 0.07f),
                    shape = RoundedCornerShape(24.dp)
                )
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.10f),
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Icon
                Icon(
                    painter = painterResource(id = R.drawable.ic_hourglass),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                
                // Title
                Text(
                    text = "Time Limit Reached",
                    style = MaterialTheme.typography.displayMedium,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // App Name
                Text(
                    text = appName,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Limit Info
                Text(
                    text = "Daily limit: ${limitMinutes}m",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.70f),
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                // Biometric Unlock Button
                GlassButton(onClick = onBiometricUnlock) {
                    Text(
                        text = "Unlock with Biometric",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // PIN Unlock Button (Secondary)
                Box(
                    modifier = Modifier
                        .background(
                            color = Color.White.copy(alpha = 0.05f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = Color.White.copy(alpha = 0.08f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable(onClick = onPinUnlock)
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = "Use PIN",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.White.copy(alpha = 0.70f)
                    )
                }
            }
        }
    }
}

/**
 * Overlay Window Manager Helper
 */
object BlockOverlayManager {
    
    fun showOverlay(windowManager: WindowManager, view: View) {
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.CENTER
        }
        
        windowManager.addView(view, params)
    }
    
    fun removeOverlay(windowManager: WindowManager, view: View) {
        try {
            windowManager.removeView(view)
        } catch (e: IllegalArgumentException) {
            // View bereits entfernt
        }
    }
}
