package com.github.simonpham.devtiles.ui.home

import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.widget.TooltipCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.github.simonpham.devtiles.R
import com.github.simonpham.devtiles.TileInfo
import com.github.simonpham.devtiles.ui.common.AdapterModel
import com.github.simonpham.devtiles.ui.common.CustomViewHolder
import com.github.simonpham.devtiles.ui.common.ViewHolderFactory
import com.github.simonpham.devtiles.util.isComponentEnabled
import com.github.simonpham.devtiles.util.toggleComponent
import kotlinx.android.synthetic.main.item_tile_toggle.view.*

/**
 * Created by Simon Pham on 6/7/18.
 * Email: simonpham.dn@gmail.com
 */

data class TileModel(val tile: TileInfo, val isAvailable: Boolean = true) : AdapterModel

class ItemTileToggleViewHolder(
        itemView: View
) : CustomViewHolder<TileModel>(itemView) {

    private val context = itemView.context

    private val vRoot = itemView.rootView
    private val tvTitle = itemView.tvTitle
    private val tvDescription = itemView.tvDescription
    private val ivIcon = itemView.ivIcon
    private val swEnabled = itemView.swEnable

    class Factory : ViewHolderFactory {
        override val layoutRes: Int = R.layout.item_tile_toggle
        override fun create(itemView: View): CustomViewHolder<*> = ItemTileToggleViewHolder(itemView)

    }

    override fun bind(model: TileModel, pos: Int) {
        model.apply {
            swEnabled.setOnCheckedChangeListener(null)
            swEnabled.isChecked = isComponentEnabled(tile.tileClass)
            swEnabled.setOnCheckedChangeListener { _, _ ->
                toggleComponent(tile.tileClass, isComponentEnabled(tile.tileClass))
                ivIcon.setImageDrawable(getTileIconDrawable(tile, isComponentEnabled(tile.tileClass)))
            }

            tvTitle.text = tile.getTitle(context.resources)
            tvDescription.text = tile.getDescription(context.resources)
            ivIcon.setImageDrawable(getTileIconDrawable(tile, isComponentEnabled(tile.tileClass)))
            TooltipCompat.setTooltipText(vRoot, tile.getDescription(context.resources))

            if (!isAvailable) {
                swEnabled.isChecked = false
            }
            toggleView(isAvailable)
        }
    }

    private fun toggleView(isAvailable: Boolean) {
        if (isAvailable) {
            swEnabled.isEnabled = true
            tvDescription.isEnabled = true
            tvTitle.isEnabled = true
            tvTitle.isEnabled = true
        } else {
            swEnabled.isEnabled = false
            tvDescription.isEnabled = false
            tvTitle.isEnabled = false
            tvTitle.isEnabled = false
        }
    }

    private fun getTileIconDrawable(tile: TileInfo, state: Boolean): Drawable {
        val tileColor = if (state) {
            ResourcesCompat.getColor(context.resources, R.color.colorAccent, null)
        } else {
            ResourcesCompat.getColor(context.resources, R.color.grey, null)
        }
        val drawable = tile.getIcon(context.resources)
        val wrapDrawable = DrawableCompat.wrap(drawable)
        DrawableCompat.setTint(wrapDrawable, tileColor)
        return drawable
    }
}