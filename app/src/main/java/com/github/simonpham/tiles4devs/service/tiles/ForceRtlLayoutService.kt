package com.github.simonpham.tiles4devs.service.tiles

import android.provider.Settings
import com.github.simonpham.tiles4devs.SYSPROP_DEBUG_FORCE_RTL
import com.github.simonpham.tiles4devs.service.BaseTileService

/**
 * Created by Simon Pham on 5/30/18.
 * Email: simonpham.dn@gmail.com
 */

class ForceRtlLayoutService : BaseTileService() {

    override fun onClick() {
        val newValue = if (isFeatureEnabled()) 0 else 1

        try {
            setGlobalInt(SYSPROP_DEBUG_FORCE_RTL, newValue)
        } catch (e: Exception) {
            showPermissionError()
        }

        refresh()
    }

    override fun isFeatureEnabled(): Boolean {
        return try {
            getGlobalInt(SYSPROP_DEBUG_FORCE_RTL) == 1
        } catch (e: Settings.SettingNotFoundException) {
            false
        }
    }
}