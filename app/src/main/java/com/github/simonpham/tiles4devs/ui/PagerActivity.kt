package com.github.simonpham.tiles4devs.ui

import android.animation.ArgbEvaluator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.github.simonpham.tiles4devs.R
import com.github.simonpham.tiles4devs.SingletonInstances
import com.github.simonpham.tiles4devs.ui.guide.RequestPermissionFragment
import com.github.simonpham.tiles4devs.ui.guide.StartDevelopingFragment
import com.github.simonpham.tiles4devs.ui.guide.WelcomeFragment
import kotlinx.android.synthetic.main.activity_pager.*


class PagerActivity : AppCompatActivity() {

    private val context = SingletonInstances.getAppContext()
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    var color1 = ContextCompat.getColor(context, R.color.lightBlue)
    var color2 = ContextCompat.getColor(context, R.color.orange)
    var color3 = ContextCompat.getColor(context, R.color.green)
    var color4 = ContextCompat.getColor(context, R.color.red)
    var colorList = intArrayOf(color1, color2, color3, color4)

    private var indicators: Array<ImageView>? = null
    var page = 0   //  to track page position

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        viewPager.adapter = mSectionsPagerAdapter

        indicators = arrayOf(intro_indicator_0, intro_indicator_1, intro_indicator_2, intro_indicator_3)
        updateIndicators(page)

        viewPager.addOnPageChangeListener((object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                val evaluator = ArgbEvaluator()
                val colorUpdate = evaluator.evaluate(positionOffset, colorList[position], colorList[if (position == 3) position else position + 1]) as Int
                viewPager.setBackgroundColor(colorUpdate)
            }

            override fun onPageSelected(position: Int) {
                page = position
                updateIndicators(page)

                when (position) {
                    0 -> viewPager.setBackgroundColor(color1)
                    1 -> viewPager.setBackgroundColor(color2)
                    2 -> viewPager.setBackgroundColor(color3)
                    3 -> viewPager.setBackgroundColor(color4)
                }
            }
        }))
    }

    override fun onBackPressed() {
        if (page > 0) {
            page -= 1
            viewPager.setCurrentItem(page, true)
        } else {
            super.onBackPressed()
        }
    }

    fun updateIndicators(position: Int) {
        for (i in 0 until indicators!!.size) {
            indicators!![i].setBackgroundResource(
                    if (i == position) R.drawable.indicator_selected else R.drawable.indicator_unselected
            )
        }
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> WelcomeFragment()
                1 -> RequestPermissionFragment()
                2 -> StartDevelopingFragment()
                3 -> StartDevelopingFragment()
                else -> throw IllegalArgumentException("Unknown selected fragment $position")
            }
        }

        override fun getCount(): Int = 4
    }
}
