package com.github.simonpham.tiles4devs.service.tiles

import android.provider.Settings
import com.github.simonpham.tiles4devs.SYSPROP_DEBUG_GPU_OVERDRAW
import com.github.simonpham.tiles4devs.service.BaseTileService

/**
 * Created by Simon Pham on 5/30/18.
 * Email: simonpham.dn@gmail.com
 */

class DebugGpuOverdrawService : BaseTileService() {

    override fun onClick() {
        val newValue = if (isFeatureEnabled()) "false" else "show"

        try {
            Settings.System.putString(contentResolver, SYSPROP_DEBUG_GPU_OVERDRAW, newValue)
        } catch (e: Exception) {
            showPermissionError()
        }

        refresh()
    }

    override fun isFeatureEnabled(): Boolean {
        return try {
            Settings.System.getString(contentResolver, SYSPROP_DEBUG_GPU_OVERDRAW) == "show"
        } catch (e: Settings.SettingNotFoundException) {
            false
        }
    }
}