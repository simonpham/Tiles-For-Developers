package com.github.simonpham.tiles4devs.service.tiles

import android.provider.Settings
import com.github.simonpham.tiles4devs.SYSPROP_DEBUG_GPU_PROFILE
import com.github.simonpham.tiles4devs.service.BaseTileService

/**
 * Created by Simon Pham on 5/31/18.
 * Email: simonpham.dn@gmail.com
 */

class ProfileGpuRenderingService : BaseTileService() {

    override fun onClick() {
        val newValue = if (isFeatureEnabled()) "false" else "visual_bars"

        try {
            Settings.System.putString(contentResolver, SYSPROP_DEBUG_GPU_PROFILE, newValue)
        } catch (e: Exception) {
            showPermissionError()
        }

        refresh()
    }

    override fun isFeatureEnabled(): Boolean {
        return try {
            Settings.System.getString(contentResolver, SYSPROP_DEBUG_GPU_PROFILE) == "visual_bars"
        } catch (e: Settings.SettingNotFoundException) {
            false
        }
    }
}