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

    protected fun showPermissionError() {
        toast(R.string.toast_permission_required)
    }

    protected fun setGlobalInt(key: String, value: Int) {
        try {
            Settings.Global.putInt(contentResolver, key, value)
        } catch (se: SecurityException) {
            showPermissionError()
        }
    }

    protected fun getGlobalInt(key: String): Int {
        var value = 0
        try {
            value = Settings.Global.getInt(contentResolver, key)
        } catch (se: SecurityException) {
            showPermissionError()
        } finally {
            return value
        }
    }
}