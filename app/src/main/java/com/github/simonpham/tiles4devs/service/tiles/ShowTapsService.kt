package com.github.simonpham.tiles4devs.service.tiles

import android.service.quicksettings.Tile
import com.github.simonpham.tiles4devs.SYSPROP_SHOW_TAPS
import com.github.simonpham.tiles4devs.service.BaseTileService

/**
 * Created by Simon Pham on 6/4/18.
 * Email: simonpham.dn@gmail.com
 */

class ShowTapsService : BaseTileService() {

    override fun refresh() {
        qsTile.state = if (isFeatureEnabled()) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
        qsTile.updateTile()
    }

    override fun onClick() {
        if (isFeatureEnabled()) {
            devSettings.setSystemInt(SYSPROP_SHOW_TAPS, 0)
        } else {
            devSettings.setSystemInt(SYSPROP_SHOW_TAPS, 1)
        }
        refresh()
    }

    private fun isFeatureEnabled(): Boolean {
        return devSettings.getSystemInt(SYSPROP_SHOW_TAPS) == 1
    }
}