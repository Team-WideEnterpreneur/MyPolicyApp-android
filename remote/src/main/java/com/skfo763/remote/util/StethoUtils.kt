package com.skfo763.remote.util

import android.app.Application
import com.facebook.stetho.Stetho

class StethoUtils {
    fun initStetho(app: Application) {
        Stetho.initializeWithDefaults(app)
    }
}