package com.hermes.ai.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    background = DeepBg,
    surface = DeepSurface,
    surfaceVariant = DeepSurfaceVariant,
    primary = DeepAccent,
    onBackground = DeepOnBg,
    onSurface = DeepOnSurface,
    onSurfaceVariant = DeepOnSurfaceVariant,
    outline = DeepOutline,
    error = DeepError,
    secondary = DeepAccentVariant,
    tertiary = DeepSuccess,
    inverseSurface = DeepCard,
    inverseOnSurface = DeepOnBg,
    surfaceTint = DeepAccent
)

private val LightColorScheme = lightColorScheme(
    background = LightBg,
    surface = LightSurface,
    surfaceVariant = LightSurfaceVariant,
    primary = LightAccent,
    onBackground = LightOnBg,
    onSurface = LightOnSurface,
    onSurfaceVariant = LightOnSurfaceVariant,
    outline = LightOutline,
    error = LightError,
    secondary = LightAccentVariant,
    tertiary = LightSuccess,
    inverseSurface = LightCard,
    inverseOnSurface = LightOnBg,
    surfaceTint = LightAccent
)

@Composable
fun HermesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
