package com.github.simonpham.tiles4devs.tiles

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import com.github.simonpham.tiles4devs.isDemoModeEnabled

/**
 * Created by Simon Pham on 5/30/18.
 * Email: simonpham.dn@gmail.com
 */

class DemoModeService : TileService() {

    override fun onTileAdded() {
        super.onTileAdded()

        if (isDemoModeEnabled()) {
            qsTile.state = Tile.STATE_ACTIVE
        } else {
            qsTile.state = Tile.STATE_INACTIVE
        }

        qsTile.updateTile()
    }
}