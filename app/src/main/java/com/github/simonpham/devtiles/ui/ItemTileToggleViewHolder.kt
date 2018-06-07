package com.github.simonpham.devtiles.ui

import android.graphics.drawable.Drawable
import android.view.View
import com.github.simonpham.devtiles.R
import com.github.simonpham.devtiles.ui.common.AdapterModel
import com.github.simonpham.devtiles.ui.common.CustomViewHolder
import com.github.simonpham.devtiles.ui.common.ViewHolderFactory
import kotlinx.android.synthetic.main.item_tile_toggle.view.*

/**
 * Created by Simon Pham on 6/7/18.
 * Email: simonpham.dn@gmail.com
 */

data class TileModel(
        val title: String,
        val description: String,
        val icon: Drawable,
        val isTileEnabled: Boolean) : AdapterModel

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

    private val tvTitle = itemView.tvTitle
    private val tvDescription = itemView.tvDescription
    private val ivIcon = itemView.ivIcon
    private val swEnabled = itemView.swEnable

    override fun bind(model: TileModel, pos: Int) {
        model.apply {
            tvTitle.text = title
            tvDescription.text = description
            ivIcon.setImageDrawable(icon)
            swEnabled.isChecked = isTileEnabled
        }
    }
}