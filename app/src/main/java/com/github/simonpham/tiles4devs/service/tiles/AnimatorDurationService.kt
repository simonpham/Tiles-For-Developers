package com.github.simonpham.tiles4devs.service.tiles

import android.graphics.drawable.Icon
import android.support.v7.app.AlertDialog
import android.view.ContextThemeWrapper
import com.github.simonpham.tiles4devs.R
import com.github.simonpham.tiles4devs.service.BaseTileService
import com.github.simonpham.tiles4devs.util.AnimatorDurationScaler
import com.github.simonpham.tiles4devs.util.AnimatorDurationScaler.getAnimatorScale
import com.github.simonpham.tiles4devs.util.AnimatorDurationScaler.getIcon

/**
 * Created by Simon Pham on 6/4/18.
 * Email: simonpham.dn@gmail.com
 */

class AnimatorDurationService : BaseTileService() {

    private val choices = arrayOf(
            "Animation off",
            "Animation scale .5x",
            "Animation scale 1x",
            "Animation scale 1.5x",
            "Animation scale 2x",
            "Animation scale 5x",
            "Animation scale 10x"
    )

    private val scales = listOf(
            0f,
            0.5f,
            1f,
            1.5f,
            2f,
            5f,
            10f)

    override fun refresh() {
        val scale = getAnimatorScale(devSettings)
        val tile = qsTile
        tile.icon = Icon.createWithResource(this, getIcon(scale))
        tile.updateTile()
    }

    override fun onClick() {
        val current = getAnimatorScale(devSettings)
        showDialog(getDialog(scales.indexOf(current)))
    }

    private fun getDialog(selectedIndex: Int): AlertDialog {
        val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AppTheme_Dialog))
        builder.setTitle(R.string.tile_animator_duration)
                .setSingleChoiceItems(choices, selectedIndex, { dialog, which ->
                    AnimatorDurationScaler.setAnimatorScale(devSettings, scales[which])
                    refresh()
                    dialog.dismiss()
                })
                .setNegativeButton(android.R.string.cancel, null)
        return builder.create()
    }
}