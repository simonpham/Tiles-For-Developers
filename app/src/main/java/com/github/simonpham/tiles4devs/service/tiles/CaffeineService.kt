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

    private val context = SingletonInstances.getAppContext()

    private val powerManager = context.getSystemService(POWER_SERVICE) as PowerManager
    @Suppress("DEPRECATION")
    private val wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "CaffeineTile")
    private val mHandler = Handler(Looper.getMainLooper())

    private var mCountdownTimer: CountDownTimer? = null

    private val mReceiver = Receiver()

    private var mSecondsRemaining: Int = 0
    private var mDuration: Int = 0
    private val DURATIONS = intArrayOf(
            5 * 60, // 5 min
            10 * 60, // 10 min
            30 * 60, // 30 min
            -1 // infinity
    )

    private var mLastClickTime: Long = -1

    override fun refresh() {
        if (wakeLock.isHeld) {
            qsTile.label = formatValueWithRemainingTime()
            qsTile.state = Tile.STATE_ACTIVE
        } else {
            qsTile.label = getString(R.string.tile_caffeine)
            qsTile.state = Tile.STATE_INACTIVE
        }
        qsTile.updateTile()
    }

    override fun onTileRemoved() {
        super.onTileRemoved()
        stopCountDown()
        mReceiver.destroy()
        if (wakeLock.isHeld) {
            wakeLock.release()
        }
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
                stopCountDown()
                if (wakeLock.isHeld) {
                    wakeLock.release()
                }
            } else {
                // change duration
                startCountDown(DURATIONS[mDuration].toLong())
                if (!wakeLock.isHeld) {
                    wakeLock.acquire()
                }
            }
        } else {
            // toggle
            if (wakeLock.isHeld) {
                wakeLock.release()
                stopCountDown()
            } else {
                wakeLock.acquire()
                mDuration = 0
                startCountDown(DURATIONS[mDuration].toLong())
            }
        }
        mLastClickTime = SystemClock.elapsedRealtime()
        refresh()
    }

    private fun startCountDown(duration: Long) {
        stopCountDown()
        mSecondsRemaining = duration.toInt()
        if (duration.toInt() == -1) {
            // infinity timing, no need to start timer
            return
        }
        mCountdownTimer = object : CountDownTimer(duration * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mSecondsRemaining = (millisUntilFinished / 1000).toInt()
                refresh()
            }

            override fun onFinish() {
                if (wakeLock.isHeld)
                    wakeLock.release()
                refresh()
            }

        }.start()
    }

    private fun stopCountDown() {
        if (mCountdownTimer != null) {
            mCountdownTimer!!.cancel()
            mCountdownTimer = null
        }
    }

    private fun formatValueWithRemainingTime(): String {
        return if (mSecondsRemaining == -1) {
            "\u221E" // infinity
        } else String.format("%02d:%02d",
                mSecondsRemaining / 60 % 60, mSecondsRemaining % 60)
    }

    inner class Receiver : BroadcastReceiver() {

        init {
            val filter = IntentFilter()
            filter.addAction(Intent.ACTION_SCREEN_OFF)
            context.registerReceiver(this, filter, null, mHandler)
        }

        fun destroy() {
            context.unregisterReceiver(this)
        }

        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent!!.action
            if (Intent.ACTION_SCREEN_OFF == action) {
                // disable caffeine if user force off (power button)
                stopCountDown()
                if (wakeLock.isHeld)
                    wakeLock.release()
                refresh()
            }
        }
    }
}