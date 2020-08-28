package com.skfo763.my_data_app.ext

import android.content.Context

fun Int.DP(context: Context): Float = (this * context.resources.displayMetrics.density)

fun Int.SP(context: Context): Float {
    return this * context.resources.displayMetrics.scaledDensity
}