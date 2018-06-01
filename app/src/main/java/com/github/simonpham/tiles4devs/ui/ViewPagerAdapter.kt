package com.github.simonpham.tiles4devs.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.github.simonpham.tiles4devs.ui.guide.RequestPermissionFragment
import com.github.simonpham.tiles4devs.ui.guide.StartDevelopingFragment
import com.github.simonpham.tiles4devs.ui.guide.WelcomeFragment

/**
 * Created by Simon Pham on 6/1/18.
 * Email: simonpham.dn@gmail.com
 */

class ViewPagerAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val PAGE_COUNT = 4

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> WelcomeFragment()
            1 -> RequestPermissionFragment()
            2 -> StartDevelopingFragment()
            3 -> StartDevelopingFragment()
            else -> throw IllegalArgumentException("Unknown selected fragment $position")
        }
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }
}