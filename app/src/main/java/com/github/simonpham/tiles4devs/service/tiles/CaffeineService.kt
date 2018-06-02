package com.github.simonpham.tiles4devs.service.tiles

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.*
import android.service.quicksettings.Tile
import com.github.simonpham.tiles4devs.R
import com.github.simonpham.tiles4devs.SingletonInstances
import com.github.simonpham.tiles4devs.service.BaseTileService


/**
 * Created by Simon Pham on 6/2/18.
 * Email: simonpham.dn@gmail.com
 */

class CaffeineService : BaseTileService() {

    private val wakeLock = SingletonInstances.getWakeLock()

    override fun refresh() {
        if (wakeLock.isHeld) {
            qsTile.label = "\u221E" // infinity
            qsTile.state = Tile.STATE_ACTIVE
        } else {
            qsTile.label = getString(R.string.tile_caffeine)
            qsTile.state = Tile.STATE_INACTIVE
        }
        qsTile.updateTile()
    }

    @SuppressLint("WakelockTimeout")
    override fun onClick() {
        if (wakeLock.isHeld) {
            wakeLock.release()
        } else {
            wakeLock.acquire()
        }
        refresh()
    }
}