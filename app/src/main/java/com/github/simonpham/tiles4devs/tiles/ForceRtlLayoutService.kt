package com.github.simonpham.tiles4devs.tiles

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import eu.chainfire.libsuperuser.Shell

/**
 * Created by Simon Pham on 5/30/18.
 * Email: simonpham.dn@gmail.com
 */

class ForceRtlLayoutService : TileService() {

    override fun onTileAdded() {
        super.onTileAdded()

        // fixme: ForceRtl change require reboot?
        qsTile.state = Tile.STATE_UNAVAILABLE
//        qsTile.state = Tile.STATE_INACTIVE

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
        Shell.SU.run("settings put global debug.force_rtl 1 | setprop ctl.restart zygote")
    }

    private fun toggleOff() {
        Shell.SU.run("settings put global debug.force_rtl 0 | setprop ctl.restart zygote")
    }
}