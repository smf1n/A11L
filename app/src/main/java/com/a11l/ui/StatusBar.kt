package com.a11l.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material.icons.filled.SignalCellularAlt
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a11l.core.D
import com.a11l.ui.theme.palette
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun StatusBar(modifier: Modifier = Modifier, onSwipeDown: () -> Unit) {
    val p = palette
    val time = remember { SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()) }

    Row(
        modifier
            .fillMaxWidth()
            .height(D.statusH)
            .background(p.bg.copy(alpha = 0.85f))
            .padding(horizontal = 16.dp)
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onDragEnd = { onSwipeDown() },
                    onVerticalDrag = { _, _ -> }
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(time, color = p.text, fontSize = 12.sp)
        Spacer(Modifier.weight(1f))
        Icon(Icons.Default.Wifi, null, tint = p.text, modifier = Modifier.size(14.dp))
        Spacer(Modifier.width(4.dp))
        Icon(Icons.Default.SignalCellularAlt, null, tint = p.text, modifier = Modifier.size(14.dp))
        Spacer(Modifier.width(4.dp))
        Icon(Icons.Default.BatteryFull, null, tint = p.text, modifier = Modifier.size(14.dp))
    }
}
