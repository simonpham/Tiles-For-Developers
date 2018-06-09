package com.github.simonpham.devtiles.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.github.simonpham.devtiles.R
import com.github.simonpham.devtiles.TileInfo
import com.github.simonpham.devtiles.ui.common.AdapterModel
import com.github.simonpham.devtiles.ui.common.HeaderModel
import com.github.simonpham.devtiles.ui.common.ItemHeaderViewHolder
import com.github.simonpham.devtiles.ui.common.MixAdapter
import com.github.simonpham.devtiles.util.isComponentEnabled
import com.github.simonpham.devtiles.util.toggleComponent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter: MixAdapter by lazy {
        MixAdapter.Builder {
            withModel<HeaderModel> { ItemHeaderViewHolder.Factory() }
            withModel<TileModel> {
                ItemTileToggleViewHolder.Factory {
                    onClick = { onSwitch(it) }
                }
            }
        }.build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = adapter

        adapter.setData(makeAdapterData())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_action, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh -> {
                // refresh
                return true
            }
            R.id.menu_settings -> {
                // settings
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun makeAdapterData(): List<AdapterModel> {
        val adapterModels = mutableListOf<AdapterModel>()

        val nonRootTiles = mutableListOf<TileInfo>()
        val magicTiles = mutableListOf<TileInfo>()

        TileInfo.values().forEach { tile ->
            if (tile.isMagicRequired) {
                magicTiles.add(tile)
            } else {
                nonRootTiles.add(tile)
            }
        }

        adapterModels.add(HeaderModel("Non-root tiles"))
        adapterModels.addAll(nonRootTiles.map {
            TileModel(it)
        })

        adapterModels.add(HeaderModel("Magic-required tiles"))
        adapterModels.addAll(magicTiles.map {
            TileModel(it)
        })

        return adapterModels
    }

    private fun onSwitch(tile: TileModel) {
        toggleComponent(tile.tile.tileClass, isComponentEnabled(tile.tile.tileClass))
    }
}
