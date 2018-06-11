package com.github.simonpham.devtiles

import android.annotation.SuppressLint
import android.content.Context
import android.os.PowerManager
import android.service.quicksettings.TileService
import com.github.simonpham.devtiles.service.tiles.CaffeineTileHelper
import com.github.simonpham.devtiles.ui.prefs.SharedPrefs

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

        fun getPowerManager() = INSTANCE.powerManagerLazy

        fun getWakeLock(): PowerManager.WakeLock = INSTANCE.wakeLockLazy

        fun getCaffeineTileHelper() = INSTANCE.caffeineTileHelperLazy

        fun getDevSettings() = INSTANCE.devSettingsLazy

        fun getPackageManager() = INSTANCE.packageManagerLazy

        fun getSharedPrefs(): SharedPrefs = INSTANCE.sharedPrefsLazy
    }

    private val powerManagerLazy by lazy { getAppContext().getSystemService(TileService.POWER_SERVICE) as PowerManager }

    @Suppress("DEPRECATION")
    private val wakeLockLazy by lazy { getPowerManager().newWakeLock(PowerManager.FULL_WAKE_LOCK, "DevTiles:CaffeineTile") }

    private val caffeineTileHelperLazy by lazy { CaffeineTileHelper() }

    private val devSettingsLazy by lazy { DeveloperSettings(getAppContext()) }

    private val packageManagerLazy by lazy { getAppContext().packageManager }

    private val sharedPrefsLazy by lazy { SharedPrefs(getAppContext()) }
}