package com.skfo763.my_data_app.extension

import android.view.View
import android.widget.TextView

fun TextView.setTextWithVisibility(text: String?, visibilityWhenNull: Int = View.GONE) {
    text?.let {
        this.text = it
        this.visibility = View.VISIBLE
    } ?: run {
        this.visibility = visibilityWhenNull
    }
}