package com.skfo763.my_data_app.ext

import android.os.Build
import android.view.View
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.core.view.isVisible

inline fun <T: View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

fun TextView.setTextCheckNull(text: String?) {
    text?.let {
        this.text = it
        visibility = View.VISIBLE
    } ?: kotlin.run {
        visibility = View.GONE
    }
}

fun TextView.setTextCheckEmpty(text: String) {
    this.text = text
    isVisible = text.isNotEmpty()
}