package com.github.simonpham.devtiles.service.tiles

import android.annotation.SuppressLint
import android.os.SystemClock
import android.service.quicksettings.Tile
import com.github.simonpham.devtiles.R
import com.github.simonpham.devtiles.SingletonInstances
import com.github.simonpham.devtiles.service.BaseTileService


/**
 * Created by Simon Pham on 6/2/18.
 * Email: simonpham.dn@gmail.com
 */

class CaffeineService : BaseTileService() {

    private val wakeLock = SingletonInstances.getWakeLock()
    private val helper = SingletonInstances.getCaffeineTileHelper()

    private var mLastClickTime: Long = -1
    private var mDuration: Int = 0

    private val DURATIONS = intArrayOf(
            5 * 60, // 5 min
            10 * 60, // 10 min
            30 * 60, // 30 min
            -1 // infinity
    )

    override fun onTileRemoved() {
        super.onTileRemoved()
        helper.handleDestroy()
    }

    override fun onCreate() {
        super.onCreate()
        helper.service = this
    }

    override fun refresh() {
        if (wakeLock.isHeld) {
            qsTile.label = helper.formatValueWithRemainingTime()
            qsTile.state = Tile.STATE_ACTIVE
        } else {
            qsTile.label = getString(R.string.tile_caffeine)
            qsTile.state = Tile.STATE_INACTIVE
        }
        qsTile.updateTile()
    }

    @SuppressLint("WakelockTimeout")
    override fun onClick() {
        if (wakeLock.isHeld && (mLastClickTime.toInt() != -1) &&
                (SystemClock.elapsedRealtime() - mLastClickTime < 5000)) {
            // cycle duration
            mDuration++
            if (mDuration >= DURATIONS.size) {
                // all durations cycled, turn if off
                mDuration = -1
                helper.stop()
                if (wakeLock.isHeld) {
                    wakeLock.release()
                }
            } else {
                // change duration
                helper.createAndStart(DURATIONS[mDuration])
                if (!wakeLock.isHeld) {
                    wakeLock.acquire()
                }
            }
        } else {
            // toggle
            if (wakeLock.isHeld) {
                wakeLock.release()
                helper.stop()
            } else {
                wakeLock.acquire()
                mDuration = 0
                helper.createAndStart(DURATIONS[mDuration])
            }
        }
        mLastClickTime = SystemClock.elapsedRealtime()
        refresh()
    }
}