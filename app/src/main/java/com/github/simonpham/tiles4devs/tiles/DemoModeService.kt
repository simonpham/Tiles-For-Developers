package com.github.simonpham.tiles4devs.tiles

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import eu.chainfire.libsuperuser.Shell

/**
 * Created by Simon Pham on 5/30/18.
 * Email: simonpham.dn@gmail.com
 */

class DemoModeService : TileService() {

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
                startDemoMode()
            } else {
                qsTile.state = Tile.STATE_INACTIVE
                stopDemoMode()
            }
        } else {
            qsTile.state = Tile.STATE_INACTIVE
        }

        qsTile.updateTile()
    }

    private fun startDemoMode() {
        Shell.SU.run("settings put global sysui_demo_allowed 1")
        Shell.SU.run("am broadcast -a com.android.systemui.demo -e command enter")
        Shell.SU.run("am broadcast -a com.android.systemui.demo -e command clock -e hhmm 1234")
        Shell.SU.run("am broadcast -a com.android.systemui.demo -e command battery -e plugged false")
        Shell.SU.run("am broadcast -a com.android.systemui.demo -e command battery -e level 100")
        Shell.SU.run("am broadcast -a com.android.systemui.demo -e command network -e wifi show -e level 4")
        Shell.SU.run("am broadcast -a com.android.systemui.demo -e command network -e mobile show -e datatype none -e level 4")
        Shell.SU.run("am broadcast -a com.android.systemui.demo -e command notifications -e visible false")
    }

    private fun stopDemoMode() {
        Shell.SU.run("am broadcast -a com.android.systemui.demo -e command exit")
    }
}