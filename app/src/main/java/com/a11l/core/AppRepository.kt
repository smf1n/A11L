package com.a11l.core

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.a11l.model.AppInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository(private val ctx: Context) {

    suspend fun loadApps(): List<AppInfo> = withContext(Dispatchers.IO) {
        val pm = ctx.packageManager
        val intent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER)
        val resolveInfos = pm.queryIntentActivities(intent, 0)

        resolveInfos.mapNotNull { ri ->
            val pkg = ri.activityInfo.packageName
            if (pkg == ctx.packageName) return@mapNotNull null
            val label = ri.loadLabel(pm).toString()
            val icon = ri.loadIcon(pm)
            AppInfo(packageName = pkg, label = label, icon = icon)
        }
            .distinctBy { it.packageName }
            .sortedBy { it.label.lowercase() }
    }

    fun launchApp(packageName: String) {
        val intent = ctx.packageManager.getLaunchIntentForPackage(packageName) ?: return
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        ctx.startActivity(intent)
    }
}
