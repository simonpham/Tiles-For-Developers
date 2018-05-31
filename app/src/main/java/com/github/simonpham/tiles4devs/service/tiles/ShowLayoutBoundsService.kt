package com.github.simonpham.tiles4devs.service.tiles

import android.provider.Settings
import com.github.simonpham.tiles4devs.SYSPROP_DEBUG_LAYOUT
import com.github.simonpham.tiles4devs.service.BaseTileService

/**
 * Created by Simon Pham on 5/30/18.
 * Email: simonpham.dn@gmail.com
 */

class ShowLayoutBoundsService : BaseTileService() {

    override fun onClick() {
        val newValue = if (isFeatureEnabled()) "false" else "true"

        try {
            Settings.System.putString(contentResolver, SYSPROP_DEBUG_LAYOUT, newValue)
        } catch (e: Exception) {
            showPermissionError()
        }

        refresh()
    }

    override fun isFeatureEnabled(): Boolean {
        return try {
            Settings.System.getString(contentResolver, SYSPROP_DEBUG_LAYOUT) == "true"
        } catch (e: Settings.SettingNotFoundException) {
            false
        }
    }
}