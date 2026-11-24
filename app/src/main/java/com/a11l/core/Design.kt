package com.a11l.core

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object D {
    val xs = 4.dp
    val s = 8.dp
    val m = 12.dp
    val l = 16.dp
    val xl = 24.dp

    val rS = 8.dp
    val rM = 12.dp
    val rL = 20.dp

    val iconSize = 52.dp
    val iconRadius = 14.dp
    val rowH = 56.dp
    val statusH = 28.dp
    val dockH = 72.dp

    val titleSize = 20.sp
    val bodySize = 14.sp
    val captionSize = 12.sp
    val miniSize = 10.sp
}

data class Palette(
    val bg: Color,
    val surface: Color,
    val surface2: Color,
    val text: Color,
    val textDim: Color,
    val accent: Color,
    val divider: Color
)

val LightPalette = Palette(
    bg = Color(0xFFFFFFFF),
    surface = Color(0xFFF1F3F4),
    surface2 = Color(0xFFE8EAED),
    text = Color(0xFF202124),
    textDim = Color(0xFF5F6368),
    accent = Color(0xFF1A73E8),
    divider = Color(0xFFDADCE0)
)

val DarkPalette = Palette(
    bg = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    surface2 = Color(0xFF2A2A2A),
    text = Color(0xFFE8EAED),
    textDim = Color(0xFF9AA0A6),
    accent = Color(0xFF8AB4F8),
    divider = Color(0xFF3C4043)
)
