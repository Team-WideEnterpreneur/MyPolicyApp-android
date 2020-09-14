package com.skfo763.repository

import android.app.Application
import com.skfo763.remote.util.StethoUtils

class StethoInitializer {

    fun init(application: Application) {
        StethoUtils().initStetho(application)
    }

}