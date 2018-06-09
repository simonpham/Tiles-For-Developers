package com.github.simonpham.devtiles

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import com.github.simonpham.devtiles.service.tiles.*

/**
 * Created by Simon Pham on 6/8/18.
 * Email: simonpham.dn@gmail.com
 */

enum class TileInfo(
        val tileClass: Class<*>,
        @StringRes val title: Int,
        @StringRes val description: Int,
        @DrawableRes val tileIcon: Int,
        val isTileEnabled: Boolean,
        val isMagicRequired: Boolean
) : TileToggle {
    TILE_ADB_WIFI(
            tileClass = AdbOverNetworkService::class.java,
            title = R.string.tile_adb_over_network,
            description = R.string.tile_adb_over_network_desc,
            tileIcon = R.drawable.tile_icon_adb_on,
            isTileEnabled = true,
            isMagicRequired = false
    ),
    TILE_ANIMATOR_DURATION(
            tileClass = AnimatorDurationService::class.java,
            title = R.string.tile_animator_duration,
            description = R.string.tile_animator_duration_desc,
            tileIcon = R.drawable.tile_icon_animator_duration,
            isTileEnabled = true,
            isMagicRequired = true
    ),
    TILE_CAFFEINE(
            tileClass = CaffeineService::class.java,
            title = R.string.tile_caffeine,
            description = R.string.tile_caffeine_desc,
            tileIcon = R.drawable.tile_icon_caffeine,
            isTileEnabled = true,
            isMagicRequired = false
    ),
    TILE_DEBUG_GPU(
            tileClass = DebugGpuOverdrawService::class.java,
            title = R.string.tile_debug_gpu_overdraw,
            description = R.string.tile_debug_gpu_overdraw_desc,
            tileIcon = R.drawable.tile_icon_debug_overdraw,
            isTileEnabled = true,
            isMagicRequired = false
    ),
    TILE_DEMO_MODE(
            tileClass = DemoModeService::class.java,
            title = R.string.tile_demo_mode,
            description = R.string.tile_demo_mode_desc,
            tileIcon = R.drawable.tile_icon_demo_mode,
            isTileEnabled = true,
            isMagicRequired = true
    ),
    TILE_FORCE_RTL(
            tileClass = ForceRtlLayoutService::class.java,
            title = R.string.tile_force_rtl_layout,
            description = R.string.tile_force_rtl_layout_desc,
            tileIcon = R.drawable.tile_icon_force_rtl,
            isTileEnabled = true,
            isMagicRequired = false
    ),
    TILE_PROFILE_GPU(
            tileClass = ProfileGpuRenderingService::class.java,
            title = R.string.tile_profile_gpu_rendering,
            description = R.string.tile_profile_gpu_rendering_desc,
            tileIcon = R.drawable.tile_icon_graphics,
            isTileEnabled = true,
            isMagicRequired = false
    ),
    TILE_DEBUG_LAYOUT(
            tileClass = ShowLayoutBoundsService::class.java,
            title = R.string.tile_show_layout_bounds,
            description = R.string.tile_show_layout_bounds_desc,
            tileIcon = R.drawable.tile_icon_show_layout,
            isTileEnabled = true,
            isMagicRequired = false
    ),
    TILE_SHOW_TAPS(
            tileClass = ShowTapsService::class.java,
            title = R.string.tile_show_taps,
            description = R.string.tile_show_taps_desc,
            tileIcon = R.drawable.tile_icon_show_taps,
            isTileEnabled = true,
            isMagicRequired = true
    ),
    TILE_STRICT_MODE(
            tileClass = StrictModeService::class.java,
            title = R.string.tile_strict_mode,
            description = R.string.tile_strict_mode_desc,
            tileIcon = R.drawable.tile_icon_strict_mode,
            isTileEnabled = true,
            isMagicRequired = false
    ),
    TILE_USB_DEBUGGING(
            tileClass = UsbDebuggingService::class.java,
            title = R.string.tile_usb_debugging,
            description = R.string.tile_usb_debugging_desc,
            tileIcon = R.drawable.tile_icon_adb,
            isTileEnabled = true,
            isMagicRequired = true
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