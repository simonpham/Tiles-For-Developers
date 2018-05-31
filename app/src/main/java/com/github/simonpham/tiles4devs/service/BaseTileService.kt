package com.github.simonpham.tiles4devs.service

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import com.github.simonpham.tiles4devs.R
import com.github.simonpham.tiles4devs.util.toast

/**
 * Created by Simon Pham on 5/31/18.
 * Email: simonpham.dn@gmail.com
 */

abstract class BaseTileService : TileService() {

    override fun onStartListening() {
        super.onStartListening()
        refresh()
    }

    protected abstract fun isFeatureEnabled(): Boolean

    protected fun refresh() {
        qsTile?.state = if (isFeatureEnabled()) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
        qsTile?.updateTile()
    }

    protected fun showPermissionError() {
        toast(R.string.toast_permission_required)
    }
}