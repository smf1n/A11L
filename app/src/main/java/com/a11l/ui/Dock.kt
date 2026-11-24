package com.a11l.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
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
            Image(
                bitmap = app.icon.toBitmap(96, 96).asImageBitmap(),
                contentDescription = app.label,
                modifier = Modifier
                    .size(52.dp)
                    .clickable { onLaunchApp(app.packageName) }
            )
        }
    }
}
