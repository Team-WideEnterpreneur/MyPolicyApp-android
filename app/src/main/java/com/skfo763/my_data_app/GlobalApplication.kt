package com.skfo763.my_data_app

import android.app.Application
import com.skfo763.repository.StethoInitializer

class GlobalApplication : Application() {

    companion object {
        const val userIdx = 1000
    }

    override fun onCreate() {
        super.onCreate()
        StethoInitializer().init(this)
    }
}