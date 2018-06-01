package com.github.simonpham.tiles4devs.util

import android.content.Context
import android.support.annotation.StringRes
import android.view.View
import android.widget.Toast

/**
 * Created by Simon Pham on 5/31/18.
 * Email: simonpham.dn@gmail.com
 */

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

private fun showToast(context: Context, message: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message ?: "null", duration).show()
}

fun Context.toast(@StringRes messageRes: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(messageRes), duration)
}

fun Context.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    showToast(this, message, duration)
}