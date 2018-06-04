package com.github.simonpham.devtiles.util

import android.support.annotation.DrawableRes
import android.support.annotation.FloatRange
import com.github.simonpham.devtiles.GLOBAL_ANIMATOR_DURATION_SCALE
import com.github.simonpham.devtiles.DeveloperSettings
import com.github.simonpham.devtiles.R

/**
 * Created by Simon Pham on 6/4/18.
 * Email: simonpham.dn@gmail.com
 */

object AnimatorDurationScaler {

    @DrawableRes
    fun getIcon(scale: Float): Int {
        if (scale <= 0f) {
            return R.drawable.tile_icon_animator_duration_off
        } else if (scale <= 0.5f) {
            return R.drawable.tile_icon_animator_duration_half_x
        } else if (scale <= 1f) {
            return R.drawable.tile_icon_animator_duration_1x
        } else if (scale <= 1.5f) {
            return R.drawable.tile_icon_animator_duration_1_5x
        } else if (scale <= 2f) {
            return R.drawable.tile_icon_animator_duration_2x
        } else if (scale <= 5f) {
            return R.drawable.tile_icon_animator_duration_5x
        } else if (scale <= 10f) {
            return R.drawable.tile_icon_animator_duration_10x
        }
        return R.drawable.tile_icon_animator_duration
    }

    fun getAnimatorScale(devSettings: DeveloperSettings): Float {
        return devSettings.getGlobalFloat(GLOBAL_ANIMATOR_DURATION_SCALE)
    }

    fun setAnimatorScale(
            devSettings: DeveloperSettings,
            @FloatRange(from = 0.0, to = 10.0) scale: Float) {
        devSettings.setGlobalFloat(GLOBAL_ANIMATOR_DURATION_SCALE, scale)
    }
}