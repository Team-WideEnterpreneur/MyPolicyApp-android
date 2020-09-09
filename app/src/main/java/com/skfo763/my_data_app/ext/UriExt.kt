package com.skfo763.my_data_app.ext

import android.net.Uri

fun String?.parseWithNullString(): Uri? {
    return if(isNullOrBlank()) {
        null
    } else {
        Uri.parse(this)
    }
}