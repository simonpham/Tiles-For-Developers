package com.github.simonpham.tiles4devs.service

import android.provider.Settings
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

    abstract fun refresh()
}