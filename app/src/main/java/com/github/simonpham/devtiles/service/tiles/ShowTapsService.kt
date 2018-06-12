package com.github.simonpham.devtiles.service.tiles

import android.service.quicksettings.Tile
import com.github.simonpham.devtiles.DATA_TYPE_INT
import com.github.simonpham.devtiles.SYSTEM_SHOW_TAPS
import com.github.simonpham.devtiles.service.BaseTileService

/**
 * Created by Simon Pham on 6/4/18.
 * Email: simonpham.dn@gmail.com
 */

class ShowTapsService : BaseTileService() {

    override fun refresh() {
        updateState(isFeatureEnabled())
    }

    override fun onClick() {
        if (isFeatureEnabled()) {
            devSettings.setSystem(SYSTEM_SHOW_TAPS, "0", DATA_TYPE_INT)
            updateState(false)
        } else {
            devSettings.setSystem(SYSTEM_SHOW_TAPS, "1", DATA_TYPE_INT)
            updateState(true)
        }
    }

    private fun isFeatureEnabled(): Boolean {
        return devSettings.getSystemInt(SYSTEM_SHOW_TAPS) == 1
    }
}