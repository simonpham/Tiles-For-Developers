package com.github.simonpham.devtiles.service.tiles

import android.content.Context
import android.graphics.drawable.Icon
import android.net.NetworkUtils
import android.net.wifi.WifiManager
import android.os.SystemProperties
import android.service.quicksettings.Tile
import com.github.simonpham.devtiles.R
import com.github.simonpham.devtiles.SYSPROP_ADB_PORT
import com.github.simonpham.devtiles.service.BaseTileService


/**
 * Created by Simon Pham on 6/2/18.
 * Email: simonpham.dn@gmail.com
 */

class AdbOverNetworkService : BaseTileService() {

    override fun refresh() {
        if (isAdbOverNetworkEnabled()) {
            val wifiManager = this.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val wifiInfo = wifiManager.connectionInfo
            if (wifiInfo != null) {
                // if wifiInfo is not null, set the label to "hostAddress"
                val address = NetworkUtils.intToInetAddress(wifiInfo.ipAddress)
                qsTile.label = address.hostAddress
            } else {
                //if wifiInfo is null, set the label without host address
                qsTile.label = this.getString(R.string.tile_adb_over_network)
            }
            qsTile.icon = Icon.createWithResource(this, R.drawable.tile_icon_adb_on)
            qsTile.state = Tile.STATE_ACTIVE
        } else {
            // Otherwise set the disabled label and icon
            qsTile.label = this.getString(R.string.tile_adb_over_network)
            qsTile.icon = Icon.createWithResource(this, R.drawable.tile_icon_adb_off)
            qsTile.state = Tile.STATE_INACTIVE
        }
        qsTile.updateTile()
    }

    override fun onClick() {
        devSettings.setSystemProp(SYSPROP_ADB_PORT,
                if (isAdbOverNetworkEnabled()) "-1" else "5555")
        refresh()
    }

    private fun isAdbOverNetworkEnabled(): Boolean {
        return SystemProperties.getInt(SYSPROP_ADB_PORT, 0) > 0
    }
}