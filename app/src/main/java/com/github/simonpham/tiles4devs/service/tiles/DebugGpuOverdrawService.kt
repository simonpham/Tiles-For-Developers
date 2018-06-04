package com.github.simonpham.tiles4devs.service.tiles

import android.os.SystemProperties
import android.service.quicksettings.Tile
import com.github.simonpham.tiles4devs.SYSPROP_DEBUG_GPU_OVERDRAW
import com.github.simonpham.tiles4devs.SingletonInstances
import com.github.simonpham.tiles4devs.service.BaseTileService

/**
 * Created by Simon Pham on 5/30/18.
 * Email: simonpham.dn@gmail.com
 */

class DebugGpuOverdrawService : BaseTileService() {

    private val devSettings = SingletonInstances.getDevSettings()

    override fun refresh() {
        val enabled = SystemProperties.get(SYSPROP_DEBUG_GPU_OVERDRAW, "false") == "show"
        qsTile.state = if (enabled) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
        qsTile.updateTile()
    }

    override fun onClick() {
        SystemProperties.set(SYSPROP_DEBUG_GPU_OVERDRAW,
                if (qsTile.state == Tile.STATE_INACTIVE) "show" else "false")
        devSettings.kickSystemService() // Settings app magic
        refresh()
    }
}