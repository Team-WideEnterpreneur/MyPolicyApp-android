package com.skfo763.my_data_app.ext

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.GradientDrawable.RECTANGLE
import androidx.annotation.ColorInt

fun getGradientDrawable(
    startColor: String,
    endColor: String,
    radius: Float = 0.0f,
    strokeColor: String = "#00000000",
    strokeSize: Float = 0.0f
) = GradientDrawable(
        GradientDrawable.Orientation.LEFT_RIGHT,
        intArrayOf(startColor.toColor(), endColor.toColor())
    ).apply {
        shape = RECTANGLE
        cornerRadius = radius * Resources.getSystem().displayMetrics.density
        setStroke(strokeSize.toInt(), strokeColor.toColor())
    }