package com.github.simonpham.devtiles.ui

import android.view.View
import androidx.appcompat.widget.TooltipCompat
import com.github.simonpham.devtiles.R
import com.github.simonpham.devtiles.TileInfo
import com.github.simonpham.devtiles.ui.common.AdapterModel
import com.github.simonpham.devtiles.ui.common.CustomViewHolder
import com.github.simonpham.devtiles.ui.common.ViewHolderFactory
import com.github.simonpham.devtiles.util.isComponentEnabled
import kotlinx.android.synthetic.main.item_tile_toggle.view.*

/**
 * Created by Simon Pham on 6/7/18.
 * Email: simonpham.dn@gmail.com
 */

data class TileModel(val tile: TileInfo) : AdapterModel

class ItemTileToggleViewHolder(
        itemView: View,
        onClick: (TileModel) -> Unit
) : CustomViewHolder<TileModel>(itemView) {

    class Factory(init: Factory.() -> Unit) : ViewHolderFactory {
        override val layoutRes: Int = R.layout.item_tile_toggle
        lateinit var onClick: (TileModel) -> Unit

        init {
            init()
        }

        override fun create(itemView: View): CustomViewHolder<*> = ItemTileToggleViewHolder(itemView, onClick)

    }

    private val context = itemView.context

    private val vRoot = itemView.rootView
    private val tvTitle = itemView.tvTitle
    private val tvDescription = itemView.tvDescription
    private val ivIcon = itemView.ivIcon
    private val swEnabled = itemView.swEnable

    override fun bind(model: TileModel, pos: Int) {
        model.apply {
            val isTileEnabled = isComponentEnabled(tile.tileClass)

            tvTitle.text = tile.getTitle(context.resources)
            tvDescription.text = tile.getDescription(context.resources)
            TooltipCompat.setTooltipText(vRoot, tile.getDescription(context.resources))
            ivIcon.setImageDrawable(tile.getIcon(context.resources))
            swEnabled.isChecked = isTileEnabled
        }
    }
}