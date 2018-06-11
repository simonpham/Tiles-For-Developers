package com.github.simonpham.devtiles.ui.guide.board

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.simonpham.devtiles.*
import com.github.simonpham.devtiles.util.gone
import com.github.simonpham.devtiles.util.show
import eu.chainfire.libsuperuser.Shell
import kotlinx.android.synthetic.main.fragment_request_permission.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Simon Pham on 6/1/18.
 * Email: simonpham.dn@gmail.com
 */

class RequestPermissionFragment : Fragment() {

    private val sharedPrefs = SingletonInstances.getSharedPrefs()

    private var isSuAvailable: Boolean = false
    private var isMagicAvailable: Boolean = false

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(KEY_SU_AVAILABILITY, isSuAvailable)
        outState.putBoolean(KEY_MAGIC_AVAILABILITY, isMagicAvailable)
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
            isMagicAvailable = savedInstanceState.getBoolean(KEY_SU_AVAILABILITY)
            if (isSuAvailable) {
                if (isMagicAvailable) {
                    gotMagicPermission()
                } else {
                    requestMagicPermission()
                }
            }
        }

        val listener = (activity as? View.OnClickListener)
                ?: (parentFragment as? View.OnClickListener)
        btnContinue.setOnClickListener(listener)
        btnSkip.setOnClickListener(listener)

        btnGrantPermission.setOnClickListener {
            requestSuPermission()
        }
    }

    private fun requestSuPermission() {
        if (context != null) {
            showProgress(getString(R.string.title_su_requesting))
        }
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
        if (context != null) {
            showProgress(getString(R.string.title_magic_gathering))
        }
        doAsync {
            Shell.SU.run("pm grant $PACKAGE_NAME android.permission.WRITE_SECURE_SETTINGS")
            Shell.SU.run("pm grant $PACKAGE_NAME android.permission.DUMP")
            uiThread {
                gotMagicPermission()
            }
        }
    }

    private fun getSuFailed() {
        isSuAvailable = false
        if (context != null) {
            showSuFail()
        }
    }

    private fun gotSuPermission() {
        isSuAvailable = true
        requestMagicPermission()
    }

    private fun gotMagicPermission() {
        sharedPrefs.magicGranted = true
        isMagicAvailable = true
        if (context != null) {
            showSuccess()
        }
    }

    private fun showProgress(title: String) {
        tvTitle.text = getString(R.string.title_processing)
        tvMiniTitle.text = title
        pbLoading.show()
        btnContinue.gone()
        btnGrantPermission.gone()
        btnSkip.gone()
    }

    private fun showSuccess() {
        tvTitle.text = getString(R.string.title_get_permission_success)
        tvMiniTitle.text = getString(R.string.title_follow_next_step)
        pbLoading.gone()
        btnContinue.show()
        btnGrantPermission.gone()
        btnSkip.gone()
    }

    private fun showSuFail() {
        tvTitle.text = getString(R.string.title_get_su_failed)
        tvMiniTitle.text = getString(R.string.title_check_again)
        pbLoading.gone()
        btnContinue.gone()
        btnGrantPermission.show()
        btnSkip.show()
    }
}