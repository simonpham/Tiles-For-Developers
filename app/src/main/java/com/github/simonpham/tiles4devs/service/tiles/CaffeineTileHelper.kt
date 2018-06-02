package com.github.simonpham.tiles4devs.service.tiles

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.os.PowerManager
import com.github.simonpham.tiles4devs.SingletonInstances


/**
 * Created by Simon Pham on 6/2/18.
 * Email: simonpham.dn@gmail.com
 */

class CaffeineTileHelper internal constructor() {

    private val context = SingletonInstances.getAppContext()
    private val wakeLock: PowerManager.WakeLock = SingletonInstances.getWakeLock()

    private val mHandler = Handler(Looper.getMainLooper())
    private val mReceiver = Receiver()
    private var mSecondsRemaining: Int = 0

    var countdownTimer: CountDownTimer? = null
    var service: CaffeineService? = null

    fun createAndStart(seconds: Int) {
        stop()
        mSecondsRemaining = seconds
        // infinity timing, no need to start timer
        if (seconds == -1) {
            return
        }
        countdownTimer = object : CountDownTimer((seconds * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mSecondsRemaining = (millisUntilFinished / 1000).toInt()
                service?.refresh()
            }

            override fun onFinish() {
                if (wakeLock.isHeld)
                    wakeLock.release()
            }
        }.start()

    }

    fun stop() {
        if (countdownTimer != null) {
            countdownTimer!!.cancel()
            countdownTimer = null
        }
    }

    fun handleDestroy() {
        stop()
        mReceiver.destroy()
        if (wakeLock.isHeld) {
            wakeLock.release()
        }
    }

    fun formatValueWithRemainingTime(): String {
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
                stop()
                if (wakeLock.isHeld)
                    wakeLock.release()
            }
        }
    }

}