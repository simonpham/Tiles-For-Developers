package com.github.simonpham.tiles4devs

import android.content.Context
import android.provider.Settings
import com.github.simonpham.tiles4devs.util.toast

/**
 * Created by Simon Pham on 5/31/18.
 * Email: simonpham.dn@gmail.com
 */

class DeveloperSettings(val context: Context) {

    fun kickSystemService() {
        SystemPropPoker().execute()
    }

    fun setGlobalInt(key: String, value: Int) {
        try {
            Settings.Global.putInt(context.contentResolver, key, value)
        } catch (se: SecurityException) {
            showPermissionError()
        }
    }

    fun getGlobalInt(key: String): Int {
        var value = 0
        try {
            value = Settings.Global.getInt(context.contentResolver, key)
        } catch (se: SecurityException) {
            showPermissionError()
        } finally {
            return value
        }
    }

    // require targetSdkVersion 22 in app build.gradle
    fun setSystemInt(key: String, value: Int) {
        try {
            Settings.System.putInt(context.contentResolver, key, value)
        } catch (se: SecurityException) {
            showPermissionError()
        } catch (e: IllegalArgumentException) {
            showPermissionError()
        }
    }

    fun getSystemInt(key: String): Int {
        var value = 0
        try {
            value = Settings.System.getInt(context.contentResolver, key)
        } catch (se: SecurityException) {
            showPermissionError()
        } finally {
            return value
        }
    }

    private fun showPermissionError() {
        context.toast(R.string.toast_permission_required)
    }
}