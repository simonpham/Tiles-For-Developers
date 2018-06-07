package com.github.simonpham.devtiles

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.content.res.ResourcesCompat

/**
 * Created by Simon Pham on 6/8/18.
 * Email: simonpham.dn@gmail.com
 */

enum class TileInfo(
        @StringRes val title: Int,
        @StringRes val description: Int,
        @DrawableRes val tileIcon: Int,
        val isTileEnabled: Boolean
) : TileToggle {
    TILE_ADB_OVER_NET(
            title = R.string.tile_adb_over_network,
            description = R.string.tile_adb_over_network,
            tileIcon = R.drawable.tile_icon_adb,
            isTileEnabled = true
    ),
    ;

    override fun getTitle(resources: Resources): String {
        return resources.getString(title)
    }

    override fun getDescription(resources: Resources): String {
        return resources.getString(description)
    }

    override fun getIcon(resources: Resources): Drawable {
        return ResourcesCompat.getDrawable(resources, tileIcon, null)!!
    }
}


interface TileToggle {
    fun getTitle(resources: Resources): String
    fun getDescription(resources: Resources): String
    fun getIcon(resources: Resources): Drawable
}