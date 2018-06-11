package com.github.simonpham.devtiles

import android.content.Context
import android.os.SystemProperties
import android.provider.Settings
import com.github.simonpham.devtiles.util.toast
import eu.chainfire.libsuperuser.Shell
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Simon Pham on 5/31/18.
 * Email: simonpham.dn@gmail.com
 */

class DeveloperSettings(val context: Context) {

    val sharedPrefs = SingletonInstances.getSharedPrefs()

    fun kickSystemService() {
        SystemPropPoker().execute()
    }

    fun checkCompatibility() {
        sharedPrefs.compatibleMode = false
        try {
            SystemProperties.set("debug.dummy", "test")
        } catch (e: RuntimeException) {
            sharedPrefs.compatibleMode = true
        }
    }

    fun setSystemProp(property: String, value: String) {
        try {
            SystemProperties.set(property, value)
        } catch (e: RuntimeException) {
            if (sharedPrefs.magicGranted) {
                doAsync {
                    Shell.SU.run("setprop $property $value")
                }
            } else {
                context.toast("Failed to set system property!\nPlease grant SU permission or re-run permission wizard")
            }
        }
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

    fun setGlobalFloat(key: String, value: Float) {
        try {
            Settings.Global.putFloat(context.contentResolver, key, value)
        } catch (se: SecurityException) {
            showPermissionError()
        }
    }

    fun getGlobalFloat(key: String, default: Float = 0f): Float {
        var value = default
        try {
            value = Settings.Global.getFloat(context.contentResolver, key)
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