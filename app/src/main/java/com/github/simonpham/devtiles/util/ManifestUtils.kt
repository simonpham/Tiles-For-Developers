package com.github.simonpham.devtiles.util

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import com.github.simonpham.devtiles.SingletonInstances

/**
 * Created by Simon Pham on 6/10/18.
 * Email: simonpham.dn@gmail.com
 */

fun toggleComponent(context: Context, component: Class<Any>, state: Boolean) {
    val componentName = ComponentName(context, component)
    val packageManager = SingletonInstances.getPackageManager()

    val newState = if (state) {
        PackageManager.COMPONENT_ENABLED_STATE_ENABLED
    } else {
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED)
    }
    packageManager.setComponentEnabledSetting(componentName, newState, PackageManager.DONT_KILL_APP)
}