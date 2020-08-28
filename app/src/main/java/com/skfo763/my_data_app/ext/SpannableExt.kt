package com.skfo763.my_data_app.ext

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import androidx.annotation.ColorInt

fun SpannableStringBuilder.setColorSpan(
    @ColorInt color: Int,
    startPoint: Int,
    endPoint: Int
) {
    setSpan(ForegroundColorSpan(color), startPoint, endPoint, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
}

fun SpannableStringBuilder.setTextStyleSpan(
    weight: Int,
    startPoint: Int,
    endPoint: Int
) {
   setSpan(StyleSpan(weight), startPoint, endPoint, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
}

fun SpannableStringBuilder.setSizeSpan(
    relativeSize: Float,
    startPoint: Int,
    endPoint: Int
) {
    setSpan(RelativeSizeSpan(relativeSize), startPoint, endPoint, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
}