package com.github.simonpham.devtiles.service

import android.service.quicksettings.TileService
import com.github.simonpham.devtiles.SingletonInstances

/**
 * Created by Simon Pham on 5/31/18.
 * Email: simonpham.dn@gmail.com
 */

abstract class BaseTileService : TileService() {

    protected val devSettings = SingletonInstances.getDevSettings()

    override fun onStartListening() {
        super.onStartListening()
        refresh()
    }

    abstract fun refresh()
}