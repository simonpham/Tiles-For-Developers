package com.github.simonpham.tiles4devs.util

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

/**
 * Created by Simon Pham on 5/31/18.
 * Email: simonpham.dn@gmail.com
 */

private fun showToast(context: Context, message: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message ?: "null", duration).show()
}

fun Context.toast(@StringRes messageRes: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(messageRes), duration)
}

fun Context.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    showToast(this, message, duration)
}