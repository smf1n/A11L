package com.a11l

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.a11l.core.AppRepository
import com.a11l.core.DarkPalette
import com.a11l.core.LightPalette
import com.a11l.model.AppInfo
import com.a11l.ui.HomeScreen
import com.a11l.ui.theme.A11LTheme

class MainActivity : ComponentActivity() {

    private lateinit var repository: AppRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        repository = AppRepository(this)

        setContent {
            var isDark by remember { mutableStateOf(false) }
            var apps by remember { mutableStateOf<List<AppInfo>>(emptyList()) }

            LaunchedEffect(Unit) {
                apps = repository.loadApps()
            }

            A11LTheme(isDark = isDark, palette = if (isDark) DarkPalette else LightPalette) {
                HomeScreen(
                    apps = apps,
                    isDark = isDark,
                    onToggleTheme = { isDark = !isDark },
                    onLaunchApp = { repository.launchApp(it) }
                )
            }
        }
    }
}
