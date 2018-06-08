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
    TILE_ADB_WIFI(
            title = R.string.tile_adb_over_network,
            description = R.string.tile_adb_over_network_desc,
            tileIcon = R.drawable.tile_icon_adb_on,
            isTileEnabled = true
    ),
    TILE_ANIMATOR_DURATION(
            title = R.string.tile_animator_duration,
            description = R.string.tile_animator_duration_desc,
            tileIcon = R.drawable.tile_icon_animator_duration,
            isTileEnabled = true
    ),
    TILE_CAFFEINE(
            title = R.string.tile_caffeine,
            description = R.string.tile_caffeine_desc,
            tileIcon = R.drawable.tile_icon_caffeine,
            isTileEnabled = true
    ),
    TILE_DEBUG_GPU(
            title = R.string.tile_debug_gpu_overdraw,
            description = R.string.tile_debug_gpu_overdraw_desc,
            tileIcon = R.drawable.tile_icon_debug_overdraw,
            isTileEnabled = true
    ),
    TILE_DEMO_MODE(
            title = R.string.tile_demo_mode,
            description = R.string.tile_demo_mode_desc,
            tileIcon = R.drawable.tile_icon_demo_mode,
            isTileEnabled = true
    ),
    TILE_FORCE_RTL(
            title = R.string.tile_force_rtl_layout,
            description = R.string.tile_force_rtl_layout_desc,
            tileIcon = R.drawable.tile_icon_force_rtl,
            isTileEnabled = true
    ),
    TILE_PROFILE_GPU(
            title = R.string.tile_profile_gpu_rendering,
            description = R.string.tile_profile_gpu_rendering_desc,
            tileIcon = R.drawable.tile_icon_graphics,
            isTileEnabled = true
    ),
    TILE_DEBUG_LAYOUT(
            title = R.string.tile_show_layout_bounds,
            description = R.string.tile_show_layout_bounds_desc,
            tileIcon = R.drawable.tile_icon_show_layout,
            isTileEnabled = true
    ),
    TILE_SHOW_TAPS(
            title = R.string.tile_show_taps,
            description = R.string.tile_show_taps_desc,
            tileIcon = R.drawable.tile_icon_show_taps,
            isTileEnabled = true
    ),
    TILE_STRICT_MODE(
            title = R.string.tile_strict_mode,
            description = R.string.tile_strict_mode_desc,
            tileIcon = R.drawable.tile_icon_strict_mode,
            isTileEnabled = true
    ),
    TILE_USB_DEBUGGING(
            title = R.string.tile_usb_debugging,
            description = R.string.tile_usb_debugging_desc,
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