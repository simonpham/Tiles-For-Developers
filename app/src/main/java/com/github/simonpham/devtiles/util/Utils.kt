package com.github.simonpham.devtiles.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Looper
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import com.github.simonpham.devtiles.*
import com.github.simonpham.devtiles.ui.guide.PagerActivity

/**
 * Created by Simon Pham on 6/1/18.
 * Email: simonpham.dn@gmail.com
 */

fun showPermissionWizard(context: Context) {
    context.startActivity(Intent(context, PagerActivity::class.java))
}

fun viewChangelog(context: Context) {
    val webView = WebView(context)
    AlertDialog.Builder(context).setTitle(R.string.title_changelog)
            .setView(webView)
            .setPositiveButton(R.string.close, null)
            .show()

    val margin = context.resources.getDimensionPixelOffset(R.dimen.margin_normal)
    (webView.layoutParams as ViewGroup.MarginLayoutParams)
            .setMargins(margin, 0, margin, 0)
    webView.loadUrl(CHANGELOG_URL)
}

fun showAboutDialog(activity: Activity) {
    val vAbout = activity.layoutInflater.inflate(R.layout.dialog_about, null, false)
    val btnRate = vAbout.findViewById(R.id.rateUs) as LinearLayout
    val btnShowChanges = vAbout.findViewById(R.id.showChanges) as LinearLayout
    val btnGitHub = vAbout.findViewById(R.id.gitRepo) as LinearLayout
    val tvVersion = vAbout.findViewById(R.id.tvVersion) as TextView
    btnRate.setOnClickListener {
        openPlayStore(activity, PACKAGE_NAME)
    }
    btnShowChanges.setOnClickListener {
        viewChangelog(activity)
    }
    btnGitHub.setOnClickListener {
        openUrl(activity, GITHUB_REPO)
    }
    tvVersion.text = String.format(activity.getString(R.string.title_version), BuildConfig.VERSION_NAME)
    AlertDialog.Builder(ContextThemeWrapper(activity, R.style.AppTheme_Dialog_About))
            .setTitle(R.string.title_about)
            .setView(vAbout)
            .create()
            .show()
}

fun showInfoDialog(context: Context, message: String) {
    AlertDialog.Builder(context)
            .setTitle("")
            .setMessage(message)
            .create()
            .show()
}

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

fun checkMainThread(method: String) {
    check(Looper.myLooper() == Looper.getMainLooper()) { "Must call $method on main thread" }
}
