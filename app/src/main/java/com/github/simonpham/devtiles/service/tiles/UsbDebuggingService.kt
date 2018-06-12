package com.github.simonpham.devtiles.service.tiles

import com.github.simonpham.devtiles.DATA_TYPE_INT
import com.github.simonpham.devtiles.GLOBAL_ADB_ENABLED
import com.github.simonpham.devtiles.service.BaseTileService

/**
 * Created by Simon Pham on 6/5/18.
 * Email: simonpham.dn@gmail.com
 */

class UsbDebuggingService : BaseTileService() {

    override fun refresh() {
        updateState(isFeatureEnabled())
    }

    override fun onClick() {
        if (isFeatureEnabled()) {
            devSettings.setGlobal(GLOBAL_ADB_ENABLED, "0", DATA_TYPE_INT)
            updateState(false)
        } else {
            devSettings.setGlobal(GLOBAL_ADB_ENABLED, "1", DATA_TYPE_INT)
            updateState(true)
        }
    }

    private fun isFeatureEnabled(): Boolean {
        return devSettings.getGlobalInt(GLOBAL_ADB_ENABLED) == 1
    }
}