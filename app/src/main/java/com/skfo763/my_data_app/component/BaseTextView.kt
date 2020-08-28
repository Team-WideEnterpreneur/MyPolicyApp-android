package com.skfo763.my_data_app.component

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.skfo763.my_data_app.R

abstract class BaseTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    var titleText: String

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attributeSet, R.styleable.BaseTextView, 0, 0)
        try {
            titleText = typedArray.getString(R.styleable.BaseTextView_tv_title) ?: ""
        } finally {
            typedArray.recycle()
        }
    }

}
