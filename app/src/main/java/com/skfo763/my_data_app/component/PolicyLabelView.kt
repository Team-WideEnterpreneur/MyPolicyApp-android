package com.skfo763.my_data_app.component

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.ext.getGradientDrawable
import com.skfo763.my_data_app.ext.toColor

class PolicyLabelView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    enum class LabelType(
        val text: String,
        val startColor: String,
        val endColor: String = startColor,
        val textColor: String = "#323232",
        val isBlink: Boolean = true
    ) {
        NONE("", "#00000000", textColor ="#00000000", isBlink = false),
        TYPE_EXPIRED("신청마감", "#BEBEBE", isBlink = false),
        TYPE_ON_AIR("신청중", "#FFCC33"),
        TYPE_WAITING( "신청대기", "#DFC197"),
        TYPE_MY_DATA("마이데이터", "#FFCC33", isBlink = false)
    }

    class Label(val type: LabelType, private val labelRadius: Float) {
        val text: String
            get() = type.text

        val backgroundDrawable: GradientDrawable
            get() = getGradientDrawable(type.startColor, type.endColor, labelRadius)

        val textColor: Int
            get() = type.textColor.toColor()

        val isBlink: Boolean
            get() = type.isBlink

        val blinkDuration: Long = 500L
    }

    private val labelView: AppCompatTextView

    var label: Label? = null
        set(value) {
            value?.let {
                labelView.text = it.text
                labelView.setTextColor(it.textColor)
                labelView.background = it.backgroundDrawable
                labelView.visibility = View.VISIBLE
            } ?: run {
                labelView.visibility = View.GONE
            }
            field = value
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_policy_label, this, true)
        labelView = findViewById(R.id.label_view)
    }

    fun startBlinkAnimation(blink: Boolean, blinkDuration: Long) {
        val anim = AnimationUtils.loadAnimation(context, R.anim.anim_label_blink).apply {
            duration = blinkDuration
        }
        if(blink) labelView.startAnimation(anim)
    }
}