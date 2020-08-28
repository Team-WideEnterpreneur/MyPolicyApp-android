package com.skfo763.my_data_app.extension

import android.util.Log

fun Any.log(message: String?) {
    Log.d(this.javaClass.name, message ?: "Unknown error")
}