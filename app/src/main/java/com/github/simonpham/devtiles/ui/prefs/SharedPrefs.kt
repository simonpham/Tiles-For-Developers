package com.github.simonpham.devtiles.ui.prefs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.annotation.StringRes
import com.github.simonpham.devtiles.R

/**
 * Created by Simon Pham on 6/9/18.
 * Email: simonpham.dn@gmail.com
 */

class SharedPrefs(private val mContext: Context) {
    private val mPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)

    var lastKnownVersionCode: Int
        get() = getInt(R.string.pref_key_last_version_code, 0)
        set(lastVersionCode) = putInt(R.string.pref_key_last_version_code, lastVersionCode)

    var compatibleMode: Boolean
        get() = getBoolean(R.string.pref_key_compatible_mode, false)
        set(mode) = putBoolean(R.string.pref_key_compatible_mode, mode)

    var suGranted: Boolean
        get() = getBoolean(R.string.pref_key_su_granted, false)
        set(isGranted) = putBoolean(R.string.pref_key_su_granted, isGranted)

    var magicGranted: Boolean
        get() = getBoolean(R.string.pref_key_magic_granted, false)
        set(isGranted) = putBoolean(R.string.pref_key_magic_granted, isGranted)

    var isFirstLaunch: Boolean
        get() = getBoolean(R.string.pref_key_is_first_launch, true)
        set(isFirstLaunch) = putBoolean(R.string.pref_key_is_first_launch, isFirstLaunch)

    private operator fun contains(@StringRes keyRes: Int): Boolean {
        return mPrefs.contains(mContext.getString(keyRes))
    }

    private fun getBoolean(@StringRes keyRes: Int, defaultValue: Boolean): Boolean {
        return mPrefs.getBoolean(mContext.getString(keyRes), defaultValue)
    }

    private fun getInt(@StringRes keyRes: Int, defaultValue: Int): Int {
        return mPrefs.getInt(mContext.getString(keyRes), defaultValue)
    }

    private fun getLong(@StringRes keyRes: Int, defaultValue: Long): Long {
        return mPrefs.getLong(mContext.getString(keyRes), defaultValue)
    }

    private fun getString(@StringRes keyRes: Int, defaultValue: String?): String? {
        return mPrefs.getString(mContext.getString(keyRes), defaultValue)
    }

    private fun getStringSet(@StringRes keyRes: Int): Set<String> {
        return mPrefs.getStringSet(mContext.getString(keyRes), emptySet())
    }

    private fun putBoolean(@StringRes keyRes: Int, value: Boolean) {
        mPrefs.edit()
                .putBoolean(mContext.getString(keyRes), value)
                .apply()
    }

    private fun putInt(@StringRes keyRes: Int, value: Int) {
        mPrefs.edit()
                .putInt(mContext.getString(keyRes), value)
                .apply()
    }

    private fun putLong(@StringRes keyRes: Int, value: Long) {
        mPrefs.edit()
                .putLong(mContext.getString(keyRes), value)
                .apply()
    }

    private fun putString(@StringRes keyRes: Int, value: String?) {
        mPrefs.edit()
                .putString(mContext.getString(keyRes), value)
                .apply()
    }

    private fun putStringSet(@StringRes keyRes: Int, value: Set<String>) {
        mPrefs.edit()
                .putStringSet(mContext.getString(keyRes), value)
                .apply()
    }

}