package com.github.simonpham.tiles4devs.tiles

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import eu.chainfire.libsuperuser.Shell

/**
 * Created by Simon Pham on 5/31/18.
 * Email: simonpham.dn@gmail.com
 */

class ProfileGpuRenderingService : TileService() {

    override fun onTileAdded() {
        super.onTileAdded()

        qsTile.state = Tile.STATE_INACTIVE

        qsTile.updateTile()
    }

    override fun onClick() {
        super.onClick()

        if (Shell.SU.available()) {
            if (qsTile.state == Tile.STATE_INACTIVE) {
                qsTile.state = Tile.STATE_ACTIVE
                toggleOn()
            } else {
                qsTile.state = Tile.STATE_INACTIVE
                toggleOff()
            }
        } else {
            qsTile.state = Tile.STATE_INACTIVE
        }

        qsTile.updateTile()
    }

    private fun toggleOn() {
        Shell.SU.run("setprop debug.hwui.profile visual_bars")
    }

    private fun toggleOff() {
        Shell.SU.run("setprop debug.hwui.profile false")
    }
}