package com.a11l.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import com.a11l.model.AppInfo
import com.a11l.ui.theme.palette

@Composable
fun HomeScreen(
    apps: List<AppInfo>,
    isDark: Boolean,
    onToggleTheme: () -> Unit,
    onLaunchApp: (String) -> Unit
) {
    val p = palette
    var showDrawer by remember { mutableStateOf(false) }
    var showQs by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize().background(p.bg)) {

        Column(
            Modifier.fillMaxSize().padding(top = 40.dp)
        ) {
            AppGrid(apps, onLaunchApp, Modifier.weight(1f))
        }

        StatusBar(
            modifier = Modifier.align(Alignment.TopCenter),
            onSwipeDown = { showQs = true }
        )

        Dock(
            apps = apps.take(5),
            onLaunchApp = onLaunchApp,
            onSwipeUp = { showDrawer = true },
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        AppDrawer(
            visible = showDrawer,
            apps = apps,
            onClose = { showDrawer = false },
            onLaunchApp = onLaunchApp
        )

        QuickSettings(
            visible = showQs,
            isDark = isDark,
            onToggleTheme = onToggleTheme,
            onClose = { showQs = false }
        )
    }
}

@Composable
private fun AppGrid(
    apps: List<AppInfo>,
    onLaunchApp: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        apps.chunked(4).take(5).forEach { row ->
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                row.forEach { app ->
                    AppIcon(app, onLaunchApp)
                }
                repeat(4 - row.size) {
                    Spacer(Modifier.width(72.dp))
                }
            }
        }
    }
}

@Composable
private fun AppIcon(app: AppInfo, onLaunch: (String) -> Unit) {
    val p = palette
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(72.dp)
            .androidx.compose.foundation.clickable { onLaunch(app.packageName) }
    ) {
        Image(
            bitmap = app.icon.toBitmap(96, 96).asImageBitmap(),
            contentDescription = app.label,
            modifier = Modifier
                .size(52.dp)
                .background(p.surface, RoundedCornerShape(14.dp))
                .padding(6.dp)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            app.label,
            color = p.text,
            fontSize = 10.sp,
            maxLines = 1
        )
    }
}
