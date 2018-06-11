package com.github.simonpham.devtiles

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import com.github.simonpham.devtiles.annotation.TileCategory
import com.github.simonpham.devtiles.service.tiles.*

/**
 * Created by Simon Pham on 6/8/18.
 * Email: simonpham.dn@gmail.com
 */

enum class TileInfo(
        val tileClass: Class<*>,
        val tileCategory: Int,
        @StringRes val title: Int,
        @StringRes val description: Int,
        @DrawableRes val tileIcon: Int
) : TileToggle {
    TILE_ADB_WIFI(
            tileClass = AdbOverNetworkService::class.java,
            tileCategory = TileCategory.SYSPROP,
            title = R.string.tile_adb_over_network,
            description = R.string.tile_adb_over_network_desc,
            tileIcon = R.drawable.tile_icon_adb_on
    ),
    TILE_ANIMATOR_DURATION(
            tileClass = AnimatorDurationService::class.java,
            tileCategory = TileCategory.MAGIC,
            title = R.string.tile_animator_duration,
            description = R.string.tile_animator_duration_desc,
            tileIcon = R.drawable.tile_icon_animator_duration
    ),
    TILE_CAFFEINE(
            tileClass = CaffeineService::class.java,
            tileCategory = TileCategory.ROOTLESS,
            title = R.string.tile_caffeine,
            description = R.string.tile_caffeine_desc,
            tileIcon = R.drawable.tile_icon_caffeine
    ),
    TILE_DEBUG_GPU(
            tileClass = DebugGpuOverdrawService::class.java,
            tileCategory = TileCategory.SYSPROP,
            title = R.string.tile_debug_gpu_overdraw,
            description = R.string.tile_debug_gpu_overdraw_desc,
            tileIcon = R.drawable.tile_icon_debug_overdraw
    ),
    TILE_DEMO_MODE(
            tileClass = DemoModeService::class.java,
            tileCategory = TileCategory.MAGIC,
            title = R.string.tile_demo_mode,
            description = R.string.tile_demo_mode_desc,
            tileIcon = R.drawable.tile_icon_demo_mode
    ),
    TILE_FORCE_RTL(
            tileClass = ForceRtlLayoutService::class.java,
            tileCategory = TileCategory.SYSPROP,
            title = R.string.tile_force_rtl_layout,
            description = R.string.tile_force_rtl_layout_desc,
            tileIcon = R.drawable.tile_icon_force_rtl
    ),
    TILE_PROFILE_GPU(
            tileClass = ProfileGpuRenderingService::class.java,
            tileCategory = TileCategory.SYSPROP,
            title = R.string.tile_profile_gpu_rendering,
            description = R.string.tile_profile_gpu_rendering_desc,
            tileIcon = R.drawable.tile_icon_graphics
    ),
    TILE_DEBUG_LAYOUT(
            tileClass = ShowLayoutBoundsService::class.java,
            tileCategory = TileCategory.SYSPROP,
            title = R.string.tile_show_layout_bounds,
            description = R.string.tile_show_layout_bounds_desc,
            tileIcon = R.drawable.tile_icon_show_layout
    ),
    TILE_SHOW_TAPS(
            tileClass = ShowTapsService::class.java,
            tileCategory = TileCategory.MAGIC,
            title = R.string.tile_show_taps,
            description = R.string.tile_show_taps_desc,
            tileIcon = R.drawable.tile_icon_show_taps
    ),
    TILE_STRICT_MODE(
            tileClass = StrictModeService::class.java,
            tileCategory = TileCategory.SYSPROP,
            title = R.string.tile_strict_mode,
            description = R.string.tile_strict_mode_desc,
            tileIcon = R.drawable.tile_icon_strict_mode
    ),
    TILE_USB_DEBUGGING(
            tileClass = UsbDebuggingService::class.java,
            tileCategory = TileCategory.MAGIC,
            title = R.string.tile_usb_debugging,
            description = R.string.tile_usb_debugging_desc,
            tileIcon = R.drawable.tile_icon_adb
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