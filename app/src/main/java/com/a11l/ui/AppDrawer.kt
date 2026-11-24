package com.a11l.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a11l.model.AppInfo
import com.a11l.ui.theme.palette

@Composable
fun AppDrawer(
    visible: Boolean,
    apps: List<AppInfo>,
    onClose: () -> Unit,
    onLaunchApp: (String) -> Unit
) {
    val p = palette
    var query by remember { mutableStateOf("") }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically { it },
        exit = fadeOut() + slideOutVertically { it }
    ) {
        Column(
            Modifier.fillMaxSize().background(p.bg).padding(top = 40.dp)
        ) {
            BasicTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(p.surface, androidx.compose.foundation.shape.RoundedCornerShape(24.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                textStyle = TextStyle(color = p.text, fontSize = 14.sp),
                decorationBox = { inner ->
                    if (query.isEmpty()) Text("Поиск приложений", color = p.textDim, fontSize = 14.sp)
                    inner()
                }
            )

            val filtered = apps.filter { it.label.contains(query, ignoreCase = true) }
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(filtered) { app ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            onLaunchApp(app.packageName)
                            onClose()
                        }
                    ) {
                        androidx.compose.foundation.Image(
                            bitmap = app.icon.toBitmap(96, 96).asImageBitmap(),
                            contentDescription = app.label,
                            modifier = Modifier.size(52.dp)
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            app.label,
                            color = p.text,
                            fontSize = 10.sp,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}
