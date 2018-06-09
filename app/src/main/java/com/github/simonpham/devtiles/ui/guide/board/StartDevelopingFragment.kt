package com.github.simonpham.devtiles.ui.guide.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.simonpham.devtiles.PACKAGE_NAME
import com.github.simonpham.devtiles.R
import com.github.simonpham.devtiles.util.openPlayStore
import kotlinx.android.synthetic.main.fragment_start_developing.*

/**
 * Created by Simon Pham on 6/1/18.
 * Email: simonpham.dn@gmail.com
 */

class StartDevelopingFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start_developing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRateUs.setOnClickListener {
            openPlayStore(it.context, PACKAGE_NAME)
            activity?.finish()
        }

        btnFinish.setOnClickListener {
            activity?.finish()
        }
    }
}