package com.skfo763.my_data_app.ext

import android.graphics.Color
import android.util.Log
import java.lang.IllegalArgumentException

fun String.toColor(): Int {
    return try {
        Color.parseColor(this)
    } catch (e: IllegalArgumentException) {
        Log.e("toColor()", e.message ?: "")
        Color.TRANSPARENT
    }
}