package com.github.simonpham.devtiles.ui.guide.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.simonpham.devtiles.R
import kotlinx.android.synthetic.main.fragment_add_tile_guide.*

/**
 * Created by Simon Pham on 6/1/18.
 * Email: simonpham.dn@gmail.com
 */

class AddTileGuideFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_tile_guide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listener = (activity as? View.OnClickListener)
                ?: (parentFragment as? View.OnClickListener)
        btnDoneAddTile.setOnClickListener(listener)
    }
}