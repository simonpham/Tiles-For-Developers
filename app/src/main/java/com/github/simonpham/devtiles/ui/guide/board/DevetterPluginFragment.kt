package com.github.simonpham.devtiles.ui.guide.board

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.simonpham.devtiles.DEVETTER_PACKAGE_NAME
import com.github.simonpham.devtiles.R
import com.github.simonpham.devtiles.util.gone
import com.github.simonpham.devtiles.util.isDevetterInstalled
import com.github.simonpham.devtiles.util.openPlayStore
import com.github.simonpham.devtiles.util.show
import kotlinx.android.synthetic.main.fragment_devetter_install.*

/**
 * Created by Simon Pham on 6/12/18.
 * Email: simonpham.dn@gmail.com
 */

class DevetterPluginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_devetter_install, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkDevetter()

        val listener = (activity as? View.OnClickListener)
                ?: (parentFragment as? View.OnClickListener)
        btnContinue.setOnClickListener(listener)

        btnInstall.setOnClickListener {
            openPlayStore(it.context, DEVETTER_PACKAGE_NAME)
        }
    }

    override fun onResume() {
        super.onResume()
        checkDevetter()
    }

    private fun checkDevetter() {
        if (isDevetterInstalled()) {
            gotDevetter()
        }
    }

    private fun gotDevetter() {
        tvTitle.text = getString(R.string.title_devetter_installed)
        tvMiniTitle.text = getString(R.string.title_devetter_continue)
        btnContinue.show()
        btnInstall.gone()
    }
}