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

    override fun onClick() {
        super.onClick()

        if (qsTile.state == Tile.STATE_INACTIVE) {
            qsTile.state = Tile.STATE_ACTIVE
            startDemoMode() // TODO
        } else {
            qsTile.state = Tile.STATE_INACTIVE
            stopDemoMode() // TODO
        }

        qsTile.updateTile()
    }

    private fun startDemoMode() {
        // TODO: implement method
    }

    private fun stopDemoMode() {
        // TODO: implement method
    }
}