package com.github.simonpham.tiles4devs.ui.guide

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.simonpham.tiles4devs.KEY_SU_AVAILABILITY
import com.github.simonpham.tiles4devs.PACKAGE_NAME
import com.github.simonpham.tiles4devs.R
import com.github.simonpham.tiles4devs.util.gone
import com.github.simonpham.tiles4devs.util.show
import eu.chainfire.libsuperuser.Shell
import kotlinx.android.synthetic.main.fragment_request_permission.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Simon Pham on 6/1/18.
 * Email: simonpham.dn@gmail.com
 */

class RequestPermissionFragment : Fragment() {

    private var isSuAvailable: Boolean = false

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(KEY_SU_AVAILABILITY, isSuAvailable)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_request_permission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            isSuAvailable = savedInstanceState.getBoolean(KEY_SU_AVAILABILITY)
            if (isSuAvailable) {
                gotSuPermission()
            }
        }

        val listener = (activity as? View.OnClickListener)
                ?: (parentFragment as? View.OnClickListener)
        btnContinue.setOnClickListener(listener)

        btnGrantPermission.setOnClickListener {
            showProgress()
            requestSuPermission()
        }
    }

    private fun requestSuPermission() {
        tvMiniTitle.text = "Requesting superuser permission..."
        doAsync {
            val result = Shell.SU.available()
            uiThread {
                if (result) {
                    gotSuPermission()
                } else {
                    getSuFailed()
                }
            }
        }
    }

    private fun requestMagicPermission() {
        tvMiniTitle.text = "Gathering \"magic\" permissions..."
        doAsync {
            Shell.SU.run("pm grant $PACKAGE_NAME android.permission.WRITE_SECURE_SETTINGS")
            Shell.SU.run("pm grant $PACKAGE_NAME android.permission.DUMP")
            uiThread {
                gotMagicPermission()
            }
        }
    }

    private fun showProgress() {
        tvTitle.text = "Processing"
        pbLoading.show()
        btnContinue.gone()
        btnGrantPermission.gone()
    }

    private fun getSuFailed() {
        tvTitle.text = "Get superuser permission failed!"
        tvMiniTitle.text = "Please check again"
        isSuAvailable = false
        pbLoading.gone()
        btnContinue.gone()
        btnGrantPermission.show()
    }

    private fun gotSuPermission() {
        isSuAvailable = true
        requestMagicPermission()
    }

    private fun gotMagicPermission() {
        tvTitle.text = "Get permissions successfully!"
        tvMiniTitle.text = "Follow the next step to add tiles to your quick settings panel"
        pbLoading.gone()
        btnContinue.show()
        btnGrantPermission.gone()
    }
}