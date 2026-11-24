package com.a11l.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a11l.core.D
import com.a11l.model.AppInfo
import com.a11l.ui.theme.palette

@Composable
fun Dock(
    apps: List<AppInfo>,
    onLaunchApp: (String) -> Unit,
    onSwipeUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val p = palette
    Row(
        modifier
            .fillMaxWidth()
            .height(D.dockH)
            .padding(horizontal = 16.dp)
            .background(p.surface, RoundedCornerShape(24.dp))
            .padding(horizontal = 12.dp)
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onDragEnd = { onSwipeUp() },
                    onVerticalDrag = { _, _ -> }
                )
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        apps.forEach { app ->
            androidx.compose.foundation.Image(
                bitmap = app.icon.toBitmap(96, 96).asImageBitmap(),
                contentDescription = app.label,
                modifier = Modifier
                    .size(48.dp)
                    .padding(4.dp)
            )
        }
    }
}
