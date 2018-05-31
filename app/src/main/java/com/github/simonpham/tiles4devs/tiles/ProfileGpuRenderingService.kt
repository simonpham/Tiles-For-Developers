package com.github.simonpham.tiles4devs.tiles

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import com.github.simonpham.tiles4devs.SystemPropertiesProxy.get
import com.github.simonpham.tiles4devs.kickSystemServices
import eu.chainfire.libsuperuser.Shell

/**
 * Created by Simon Pham on 5/31/18.
 * Email: simonpham.dn@gmail.com
 */

class ProfileGpuRenderingService : TileService() {

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
        val value = get(applicationContext, "debug.hwui.profile", "false")
        qsTile.state = if (value == "visual_bars")
            Tile.STATE_ACTIVE
        else
            Tile.STATE_INACTIVE
        qsTile.updateTile()
    }

    private fun toggleOn() {
        Shell.SU.run("setprop debug.hwui.profile visual_bars")
        kickSystemServices()
    }

    private fun toggleOff() {
        Shell.SU.run("setprop debug.hwui.profile false")
        kickSystemServices()
    }
}