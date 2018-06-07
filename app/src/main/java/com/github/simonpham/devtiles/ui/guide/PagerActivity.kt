package com.github.simonpham.devtiles.ui.guide

import android.animation.ArgbEvaluator
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.github.simonpham.devtiles.PAGE_COUNT
import com.github.simonpham.devtiles.R
import com.github.simonpham.devtiles.SingletonInstances
import kotlinx.android.synthetic.main.activity_pager.*


class PagerActivity : AppCompatActivity(), View.OnClickListener {

    // magic
    override fun onClick(v: View?) {
        nextPage()
    }

    private val context = SingletonInstances.getAppContext()

    private var color1 = ContextCompat.getColor(context, R.color.lightBlue)
    private var color2 = ContextCompat.getColor(context, R.color.green)
    private var color3 = ContextCompat.getColor(context, R.color.purple)
    private var color4 = ContextCompat.getColor(context, R.color.red)
    var colorList = intArrayOf(color1, color2, color3, color4)

    private var indicators: Array<ImageView>? = null
    var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 2

        indicators = arrayOf(intro_indicator_0, intro_indicator_1, intro_indicator_2, intro_indicator_3)

        viewPager.addOnPageChangeListener((object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                val evaluator = ArgbEvaluator()
                val colorUpdate = evaluator.evaluate(positionOffset, colorList[position], colorList[if (position == 3) position else position + 1]) as Int
                updateColor(colorUpdate)
            }

            override fun onPageSelected(position: Int) {
                page = position
                updateIndicators(page)
            }
        }))

        updateIndicators(page)
    }

    private fun updateColor(color: Int) {
        viewPager.setBackgroundColor(color)
        window.statusBarColor = color
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

    private fun nextPage() {
        page = (page + 1) % PAGE_COUNT
        viewPager.currentItem = page
    }
}
