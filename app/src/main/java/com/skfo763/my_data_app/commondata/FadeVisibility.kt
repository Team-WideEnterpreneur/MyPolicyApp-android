package com.skfo763.my_data_app.commondata

import android.view.View

class FadeVisibility(
    val isVisible: Boolean,
    val duration: Long = 500L,
    val goneOrInVisible: Int = View.GONE,
    val onAnimationEnd: (() -> Unit)? = null
)