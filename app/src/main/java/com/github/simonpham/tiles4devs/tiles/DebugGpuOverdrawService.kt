package com.github.simonpham.tiles4devs.tiles

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import eu.chainfire.libsuperuser.Shell

/**
 * Created by Simon Pham on 5/30/18.
 * Email: simonpham.dn@gmail.com
 */

class DebugGpuOverdrawService : TileService() {

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
                startDebugOverdraw()
            } else {
                qsTile.state = Tile.STATE_INACTIVE
                stopDebugOverdraw()
            }
        } else {
            qsTile.state = Tile.STATE_INACTIVE
        }

        qsTile.updateTile()
    }

    private fun startDebugOverdraw() {
        Shell.SU.run("setprop debug.hwui.overdraw show")
    }

    private fun stopDebugOverdraw() {
        Shell.SU.run("setprop debug.hwui.overdraw false")
    }
}