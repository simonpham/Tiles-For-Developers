package com.github.simonpham.tiles4devs.tiles

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import com.github.simonpham.tiles4devs.SystemPropertiesProxy
import com.github.simonpham.tiles4devs.kickSystemServices
import eu.chainfire.libsuperuser.Shell

/**
 * Created by Simon Pham on 5/30/18.
 * Email: simonpham.dn@gmail.com
 */

class ForceRtlLayoutService : TileService() {

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
        val enabled = SystemPropertiesProxy.getBoolean(applicationContext, "debug.force_rtl", false)
        qsTile.state = if (enabled) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
        qsTile.updateTile()
    }

    private fun toggleOn() {
        // fixme: ForceRtl change require reboot?
        Shell.SU.run("settings put global debug.force_rtl 1 | setprop ctl.restart zygote")
        kickSystemServices()
    }

    private fun toggleOff() {
        Shell.SU.run("settings put global debug.force_rtl 0 | setprop ctl.restart zygote")
        kickSystemServices()
    }
}