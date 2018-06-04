package com.github.simonpham.devtiles.service.tiles

import android.service.quicksettings.Tile
import com.github.simonpham.devtiles.GLOBAL_ADB_ENABLED
import com.github.simonpham.devtiles.service.BaseTileService

/**
 * Created by Simon Pham on 6/5/18.
 * Email: simonpham.dn@gmail.com
 */

class UsbDebuggingService : BaseTileService() {

    override fun refresh() {
        qsTile.state = if (isFeatureEnabled()) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
        qsTile.updateTile()
    }

    override fun onClick() {
        if (isFeatureEnabled()) {
            devSettings.setGlobalInt(GLOBAL_ADB_ENABLED, 0)
        } else {
            devSettings.setGlobalInt(GLOBAL_ADB_ENABLED, 1)
        }
        refresh()
    }

    private fun isFeatureEnabled(): Boolean {
        return devSettings.getGlobalInt(GLOBAL_ADB_ENABLED) == 1
    }
}