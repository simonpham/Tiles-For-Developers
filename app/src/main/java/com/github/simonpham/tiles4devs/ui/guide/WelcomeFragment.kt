package com.github.simonpham.tiles4devs.ui.guide

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.simonpham.tiles4devs.PAGE_REQUEST_PERMISSION
import com.github.simonpham.tiles4devs.R
import kotlinx.android.synthetic.main.fragment_welcome.*

/**
 * Created by Simon Pham on 6/1/18.
 * Email: simonpham.dn@gmail.com
 */

class WelcomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager = activity!!.findViewById(R.id.viewPager) as ViewPager
        btnGettingStarted.setOnClickListener {
            viewPager.currentItem = PAGE_REQUEST_PERMISSION
        }
    }
}