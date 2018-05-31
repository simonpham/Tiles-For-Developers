package com.github.simonpham.tiles4devs

import android.app.Application

/**
 * Created by Simon Pham on 6/1/18.
 * Email: simonpham.dn@gmail.com
 */

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        SingletonInstances.init(this)
    }
}