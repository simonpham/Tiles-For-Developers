package com.github.simonpham.tiles4devs.tiles

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import com.github.simonpham.tiles4devs.SystemPropertiesProxy.getBoolean
import com.github.simonpham.tiles4devs.kickSystemServices
import eu.chainfire.libsuperuser.Shell

/**
 * Created by Simon Pham on 5/30/18.
 * Email: simonpham.dn@gmail.com
 */

class ShowLayoutBoundsService : TileService() {

    override fun onStartListening() {
        super.onStartListening()
        refresh()
    }

    override fun onClick() {
        super.onClick()

        if (qsTile.state == Tile.STATE_INACTIVE) {
            toggleOn()
        } else {
            toggleOff()
        }

        refresh()
    }

    private fun refresh() {
        val enabled = getBoolean(applicationContext, "debug.layout", false)
        qsTile.state = if (enabled) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
        qsTile.updateTile()
    }

    private fun toggleOn() {
        Shell.SU.run("setprop debug.layout true")
        kickSystemServices()
    }

    private fun toggleOff() {
        Shell.SU.run("setprop debug.layout false")
        kickSystemServices()
    }
}