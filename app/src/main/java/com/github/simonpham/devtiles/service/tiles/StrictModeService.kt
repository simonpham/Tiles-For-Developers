package com.github.simonpham.devtiles.service.tiles

import android.os.SystemProperties
import android.service.quicksettings.Tile
import com.github.simonpham.devtiles.R
import com.github.simonpham.devtiles.SYSPROP_STRICT_MODE_DISABLE
import com.github.simonpham.devtiles.SYSPROP_STRICT_MODE_VISUAL
import com.github.simonpham.devtiles.service.BaseTileService

/**
 * Created by Simon Pham on 6/5/18.
 * Email: simonpham.dn@gmail.com
 */

class StrictModeService : BaseTileService() {

    override fun refresh() {
        val enabled = SystemProperties.getInt(SYSPROP_STRICT_MODE_DISABLE, 1) == 0
        if (enabled) {
            qsTile.label = getString(R.string.tile_strict_mode_enabled)
            qsTile.state = Tile.STATE_ACTIVE
        } else {
            qsTile.label = getString(R.string.tile_strict_mode_disabled)
            qsTile.state = Tile.STATE_INACTIVE
        }
        qsTile.updateTile()
    }

    override fun onClick() {
        if (qsTile.state == Tile.STATE_INACTIVE) {
            devSettings.setSystemProp(SYSPROP_STRICT_MODE_DISABLE, "0")
            devSettings.setSystemProp(SYSPROP_STRICT_MODE_VISUAL, "1")
        } else {
            devSettings.setSystemProp(SYSPROP_STRICT_MODE_DISABLE, "1")
            devSettings.setSystemProp(SYSPROP_STRICT_MODE_VISUAL, "0")
        }
        devSettings.kickSystemService() // Settings app magic
        refresh()
    }
}