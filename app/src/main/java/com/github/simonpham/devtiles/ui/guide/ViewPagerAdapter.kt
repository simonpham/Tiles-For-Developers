package com.github.simonpham.devtiles.ui.guide

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.github.simonpham.devtiles.*
import com.github.simonpham.devtiles.ui.guide.board.AddTileGuideFragment
import com.github.simonpham.devtiles.ui.guide.board.RequestPermissionFragment
import com.github.simonpham.devtiles.ui.guide.board.StartDevelopingFragment
import com.github.simonpham.devtiles.ui.guide.board.WelcomeFragment

/**
 * Created by Simon Pham on 6/1/18.
 * Email: simonpham.dn@gmail.com
 */

class ViewPagerAdapter internal constructor(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): androidx.fragment.app.Fragment? {
        return when (position) {
            PAGE_WELCOME -> WelcomeFragment()
            PAGE_REQUEST_PERMISSION -> RequestPermissionFragment()
            PAGE_ADD_TILE_GUIDE -> AddTileGuideFragment()
            PAGE_START_DEVELOPING -> StartDevelopingFragment()
            else -> throw IllegalArgumentException("Unknown selected fragment $position")
        }
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }
}