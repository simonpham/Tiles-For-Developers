package com.github.simonpham.devtiles.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.github.simonpham.devtiles.BuildConfig
import com.github.simonpham.devtiles.R
import com.github.simonpham.devtiles.SingletonInstances
import com.github.simonpham.devtiles.TileInfo
import com.github.simonpham.devtiles.annotation.TileCategory
import com.github.simonpham.devtiles.ui.common.AdapterModel
import com.github.simonpham.devtiles.ui.common.HeaderModel
import com.github.simonpham.devtiles.ui.common.ItemHeaderViewHolder
import com.github.simonpham.devtiles.ui.common.MixAdapter
import com.github.simonpham.devtiles.util.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private val sharedPrefs = SingletonInstances.getSharedPrefs()
    private val devSettings = SingletonInstances.getDevSettings()

    private val adapter: MixAdapter by lazy {
        MixAdapter.Builder {
            withModel<HeaderModel> { ItemHeaderViewHolder.Factory() }
            withModel<TileModel> { ItemTileToggleViewHolder.Factory() }
        }.build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPrefs = SingletonInstances.getSharedPrefs()
        if (sharedPrefs.isFirstLaunch
                || sharedPrefs.lastKnownVersionCode < BuildConfig.VERSION_CODE) {
            sharedPrefs.isFirstLaunch = false
            showPermissionWizard(this)
        }

        setContentView(R.layout.activity_main)
        supportActionBar?.title = getString(R.string.app_title)

        recyclerView.adapter = adapter
        refresh()

        if (sharedPrefs.lastKnownVersionCode < BuildConfig.VERSION_CODE) {
            viewChangelog(this)
            sharedPrefs.lastKnownVersionCode = BuildConfig.VERSION_CODE
        }

        swiperefresh.setOnRefreshListener {
            refresh()
        }
    }

    private fun refresh() {
        swiperefresh.isRefreshing = true
        doAsync {
            devSettings.checkCompatibility()
            val data = makeAdapterData()
            uiThread {
                adapter.setData(data)
                swiperefresh.isRefreshing = false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_action, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh -> {
                refresh()
                return true
            }
            R.id.menu_wizard -> {
                showPermissionWizard(this)
                return true
            }
            R.id.menu_settings -> {
                // settings
                toast("Not implemented!")
                return true
            }
            R.id.menu_about -> {
                showAboutDialog(this)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun makeAdapterData(): List<AdapterModel> {
        val adapterModels = mutableListOf<AdapterModel>()

        val nonRootTiles = mutableListOf<TileInfo>()
        val syspropTiles = mutableListOf<TileInfo>()
        val rootTiles = mutableListOf<TileInfo>()
        val magicTiles = mutableListOf<TileInfo>()
        val unavailableTiles = mutableListOf<TileInfo>()

        TileInfo.values().forEach { tile ->
            when (tile.tileCategory) {
                TileCategory.ROOTLESS -> nonRootTiles.add(tile)
                TileCategory.SYSPROP -> {
                    if (!sharedPrefs.compatibleMode) {
                        syspropTiles.add(tile)
                    } else if (sharedPrefs.suGranted) {
                        rootTiles.add(tile)
                    } else {
                        unavailableTiles.add(tile)
                    }
                }
                TileCategory.MAGIC -> {
                    if (isDevetterInstalled() && sharedPrefs.magicGranted) {
                        magicTiles.add(tile)
                    } else {
                        unavailableTiles.add(tile)
                    }
                }
            }
        }

        adapterModels.run {
            if (nonRootTiles.size > 0) {
                add(HeaderModel(
                        getString(R.string.header_rootless_tiles),
                        getString(R.string.header_rootless_tiles_tip)
                ))
                addAll(nonRootTiles.map {
                    TileModel(it)
                })
            }

            if (syspropTiles.size > 0) {
                add(HeaderModel(
                        getString(R.string.header_sys_prop_tiles),
                        getString(R.string.header_sys_prop_tiles_tip)
                ))
                addAll(syspropTiles.map {
                    TileModel(it)
                })
            }

            if (rootTiles.size > 0) {
                add(HeaderModel(
                        getString(R.string.header_root_tiles),
                        getString(R.string.header_root_tiles_tip)
                ))
                addAll(rootTiles.map {
                    TileModel(it)
                })
            }

            if (magicTiles.size > 0) {
                add(HeaderModel(
                        getString(R.string.header_magic_required_tiles),
                        getString(R.string.header_magic_required_tiles_tip)
                ))
                addAll(magicTiles.map {
                    TileModel(it)
                })
            }

            if (unavailableTiles.size > 0) {
                add(HeaderModel(
                        getString(R.string.header_unavailable_tiles),
                        getString(R.string.header_unavailable_tiles_tip)
                ))
                addAll(unavailableTiles.map {
                    TileModel(it, false)
                })
            }
        }

        return adapterModels
    }
}
