package com.github.simonpham.tiles4devs.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.github.simonpham.tiles4devs.R

/**
 * Created by Simon Pham on 6/1/18.
 * Email: simonpham.dn@gmail.com
 */

fun openUrl(context: Context, url: String, message: String = "Open $url") {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(Intent.createChooser(intent, message))
}

fun openPlayStore(context: Context, packageName: String) {
    try {
        context.startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse("market://details?id=$packageName")))
    } catch (activityNotFound: Throwable) {
        openUrl(context, "https://play.google.com/store/apps/details?id=$packageName",
                context.getString(R.string.open_play_store))
    }
}
