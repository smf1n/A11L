package com.a11l.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.a11l.core.Palette

val LocalPalette = staticCompositionLocalOf { com.a11l.core.LightPalette }

@Composable
fun A11LTheme(
    isDark: Boolean,
    palette: Palette,
    content: @Composable () -> Unit
) {
    val scheme = if (isDark) {
        darkColorScheme(
            primary = palette.accent,
            background = palette.bg,
            surface = palette.surface,
            onPrimary = Color.White,
            onBackground = palette.text,
            onSurface = palette.text
        )
    } else {
        lightColorScheme(
            primary = palette.accent,
            background = palette.bg,
            surface = palette.surface,
            onPrimary = Color.White,
            onBackground = palette.text,
            onSurface = palette.text
        )
    }

    CompositionLocalProvider(LocalPalette provides palette) {
        MaterialTheme(colorScheme = scheme, content = content)
    }
}

val palette: Palette
    @Composable get() = LocalPalette.current
