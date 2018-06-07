package com.github.simonpham.devtiles.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.simonpham.devtiles.R
import com.github.simonpham.devtiles.TileInfo
import com.github.simonpham.devtiles.ui.common.AdapterModel
import com.github.simonpham.devtiles.ui.common.HeaderModel
import com.github.simonpham.devtiles.ui.common.ItemHeaderViewHolder
import com.github.simonpham.devtiles.ui.common.MixAdapter
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

    private fun makeAdapterData(): List<AdapterModel> {
        val adapterModels = mutableListOf<AdapterModel>()
        adapterModels.add(HeaderModel("Non-root tiles"))
        adapterModels.addAll(TileInfo.values().map {
            TileModel(it)
        })
        return adapterModels
    }

    private fun onSwitch(tile: TileModel) {
        return
    }
}
