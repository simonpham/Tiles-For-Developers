package com.github.simonpham.tiles4devs

import android.annotation.SuppressLint
import android.content.Context

/**
 * Created by Simon Pham on 6/1/18.
 * Email: simonpham.dn@gmail.com
 */

class SingletonInstances private constructor(private val appContext: Context) {

    companion object {
        
        @SuppressLint("StaticFieldLeak")
        private lateinit var INSTANCE: SingletonInstances
        private var initialized = false

        fun init(context: Context) {
            check(!initialized, { "Only init once" })
            INSTANCE = SingletonInstances(context.applicationContext)
            initialized = true
        }

        fun getAppContext() = INSTANCE.appContext
    }
}