package com.github.simonpham.devtiles

import android.annotation.SuppressLint
import android.content.Context
import android.os.PowerManager
import android.service.quicksettings.TileService
import com.github.simonpham.devtiles.service.tiles.CaffeineTileHelper

/**
 * Created by Simon Pham on 6/1/18.
 * Email: simonpham.dn@gmail.com
 */

class SingletonInstances private constructor(private val appContext: Context) {

    companion object {

        @SuppressLint("StaticFieldLeak")
        private lateinit var INSTANCE: SingletonInstances
        private var initialized = false

        fun init(context: Context) {
            check(!initialized, { "Only init once" })
            INSTANCE = SingletonInstances(context.applicationContext)
            initialized = true
        }

        fun getAppContext() = INSTANCE.appContext
        fun getPowerManager() = INSTANCE.powerManager
        fun getWakeLock(): PowerManager.WakeLock = INSTANCE.wakeLock
        fun getCaffeineTileHelper() = INSTANCE.caffeineTileHelper
        fun getDevSettings() =  INSTANCE.devSettings
        fun getPackageManager() = INSTANCE.packageManager
    }

    private val powerManager by lazy { getAppContext().getSystemService(TileService.POWER_SERVICE) as PowerManager }
    @Suppress("DEPRECATION")
    private val wakeLock by lazy { getPowerManager().newWakeLock(PowerManager.FULL_WAKE_LOCK, "DevTiles:CaffeineTile") }
    private val caffeineTileHelper by lazy { CaffeineTileHelper() }
    private val devSettings by lazy { DeveloperSettings(getAppContext()) }
    private val packageManager by lazy { getAppContext().packageManager }
}