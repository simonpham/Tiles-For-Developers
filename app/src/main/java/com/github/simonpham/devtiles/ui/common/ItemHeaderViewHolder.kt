package com.github.simonpham.devtiles.ui.common

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.github.simonpham.devtiles.R
import com.github.simonpham.devtiles.util.showInfoDialog
import kotlinx.android.synthetic.main.item_header.view.*

/**
 * Created by Khang NT on 1/5/18.
 * Email: khang.neon.1997@gmail.com
 */


class HeaderModel(val header: String, val tooltip: String = "") : AdapterModel, HasIdLong {
    override val idLong: Long by lazy { IdGenerator.idFor(header) }
}

class ItemHeaderViewHolder(itemView: View) : CustomViewHolder<HeaderModel>(itemView) {
    private val tvHeader: TextView = itemView.tvHeader
    private val btnHelp: ImageView = itemView.btnHelp

    override fun bind(model: HeaderModel, pos: Int) {
        tvHeader.text = model.header
        if (model.tooltip != "") {
            btnHelp.visibility = View.VISIBLE
            btnHelp.setOnClickListener {
                showInfoDialog(itemView.context, model.tooltip)
            }
        } else {
            btnHelp.visibility = View.GONE
        }
    }

    class Factory : ViewHolderFactory {
        override val layoutRes: Int = R.layout.item_header

        override fun create(itemView: View): CustomViewHolder<*> {
            return ItemHeaderViewHolder(itemView)
        }
    }

}