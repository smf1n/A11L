package com.a11l.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a11l.core.D
import com.a11l.ui.theme.palette
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun QuickSettings(
    visible: Boolean,
    isDark: Boolean,
    onToggleTheme: () -> Unit,
    onClose: () -> Unit
) {
    val p = palette
    val time = remember { SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()) }
    val date = remember { SimpleDateFormat("EEE, d MMM", Locale.getDefault()).format(Date()) }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically { -it },
        exit = fadeOut() + slideOutVertically { -it }
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(p.bg)
                .padding(16.dp)
                .clickable { onClose() }
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text(time, color = p.text, fontSize = 32.sp)
                    Text(date, color = p.textDim, fontSize = 12.sp)
                }
            }
            Spacer(Modifier.height(20.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                QsTile(Icons.Default.Wifi, "Wi-Fi", p.surface, p.text)
                QsTile(Icons.Default.Bluetooth, "Bluetooth", p.surface, p.text)
                QsTile(Icons.Default.FlashlightOn, "Фонарик", p.surface, p.text)
                QsTile(
                    Icons.Default.DarkMode, 
                    if (isDark) "Светлая" else "Тёмная", 
                    p.accent,
                    androidx.compose.ui.graphics.Color.White,
                    onToggleTheme
                )
            }
        }
    }
}

@Composable
private fun QsTile(
    icon: ImageVector,
    label: String,
    bg: androidx.compose.ui.graphics.Color,
    fg: androidx.compose.ui.graphics.Color,
    onClick: () -> Unit = {}
) {
    val p = palette
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(76.dp)
            .background(bg, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(vertical = 12.dp)
    ) {
        Icon(icon, null, tint = fg, modifier = Modifier.size(24.dp))
        Spacer(Modifier.height(6.dp))
        Text(label, color = fg, fontSize = 10.sp)
    }
}
