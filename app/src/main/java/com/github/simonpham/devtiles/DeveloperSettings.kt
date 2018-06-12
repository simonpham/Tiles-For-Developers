package com.github.simonpham.devtiles

import android.content.ComponentName
import android.content.Context
import android.content.Intent
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

    fun setSystemProp(property: String, value: String, kickNeeded: Boolean = false) {
        try {
            SystemProperties.set(property, value)
            if (kickNeeded) {
                kickSystemService() // Settings app magic
            }
        } catch (e: RuntimeException) {
            if (sharedPrefs.magicGranted) {
                doAsync {
                    Shell.SU.run("setprop $property $value")
                    uiThread {
                        if (kickNeeded) {
                            kickSystemService() // Settings app magic
                        }
                    }
                }
            } else {
                context.toast("Failed to set system property!\nPlease grant SU permission or re-run permission wizard")
            }
        }
    }

    fun setGlobal(key: String, value: String, dataType: Int = DATA_TYPE_STRING) {
        val intent = Intent(ACTION_PUT_GLOBAL)
                .setComponent(ComponentName(
                        "com.github.simonpham.devetter",
                        "com.github.simonpham.devetter.DevetterService"
                ))
                .putExtra(EXTRA_KEY, key)
                .putExtra(EXTRA_VALUE, value)
                .putExtra(EXTRA_VALUE_TYPE, dataType)
        context.startService(intent)
    }

    fun setSecure(key: String, value: String, dataType: Int = DATA_TYPE_STRING) {
        val intent = Intent(ACTION_PUT_SECURE)
                .setComponent(ComponentName(
                        "com.github.simonpham.devetter",
                        "com.github.simonpham.devetter.DevetterService"
                ))
                .putExtra(EXTRA_KEY, key)
                .putExtra(EXTRA_VALUE, value)
                .putExtra(EXTRA_VALUE_TYPE, dataType)
        context.startService(intent)
    }

    fun setSystem(key: String, value: String, dataType: Int = DATA_TYPE_STRING) {
        val intent = Intent(ACTION_PUT_SYSTEM)
                .setComponent(ComponentName(
                        "com.github.simonpham.devetter",
                        "com.github.simonpham.devetter.DevetterService"
                ))
                .putExtra(EXTRA_KEY, key)
                .putExtra(EXTRA_VALUE, value)
                .putExtra(EXTRA_VALUE_TYPE, dataType)
        context.startService(intent)
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