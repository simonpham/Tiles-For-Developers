package com.github.simonpham.devtiles.ui

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

data class Tile(val title: String, val description: String, val icon: Int, val isTileEnabled: Boolean) : AdapterModel

class ItemTileToggleViewHolder(itemView: View) : CustomViewHolder<Tile>(itemView) {

    class Factory : ViewHolderFactory {
        override val layoutRes: Int = R.layout.item_tile_toggle
        override fun create(itemView: View): CustomViewHolder<*> = ItemTileToggleViewHolder(itemView)
    }

    private val context = itemView.context

    private val tvTitle = itemView.tvTitle
    private val tvDescription = itemView.tvDescription
    private val ivIcon = itemView.ivIcon
    private val swEnabled = itemView.swEnable

    override fun bind(model: Tile, pos: Int) {
        model.apply {
            tvTitle.text = title
            tvDescription.text = description
            ivIcon.setImageResource(icon)
            swEnabled.isChecked = isTileEnabled
        }
    }
}