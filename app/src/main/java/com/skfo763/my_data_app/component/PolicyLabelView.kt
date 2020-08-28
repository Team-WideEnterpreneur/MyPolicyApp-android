package com.skfo763.my_data_app.component

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.databinding.ViewPolicyLabelBinding
import com.skfo763.my_data_app.ext.DP
import com.skfo763.my_data_app.ext.getGradientDrawable
import com.skfo763.my_data_app.ext.toColor

class PolicyLabelView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attributeSet, defStyleAttr) {

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

        val blinkDuration: Long = 3000L
    }

    private val binding = ViewPolicyLabelBinding.inflate(
        LayoutInflater.from(context), null, true)

    private val _label = MutableLiveData<Label>()

    val label: LiveData<Label> = _label

    init {
        binding.view = this
        binding.lifecycleOwner = context as AppCompatActivity
    }

    fun setLabel(label: Label?) {
        binding.labelView.visibility = if(label != null) View.VISIBLE else View.GONE
        _label.value = label
    }

    fun startBlinkAnimation(blink: Boolean, blinkDuration: Long) {
        val anim = AnimationUtils.loadAnimation(context, R.anim.anim_label_blink).apply {
            duration = blinkDuration
        }
        if(blink) binding.labelView.startAnimation(anim)
    }
}