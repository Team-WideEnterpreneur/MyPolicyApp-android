package com.skfo763.my_data_app.utils

import android.animation.Animator
import android.text.SpannableStringBuilder
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.commondata.FadeVisibility
import com.skfo763.my_data_app.component.BadgeTabLayout
import com.skfo763.my_data_app.component.PolicyLabelView
import com.skfo763.my_data_app.ext.DP
import com.skfo763.my_data_app.ui.maintab.adapter.MainPagerAdapter
import com.skfo763.my_data_app.ui.maintab.data.MainServiceFragmentList
import com.skfo763.my_data_app.ui.maintab.data.MainServiceType

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("fragmentList")
    fun ViewPager2.setFragmentList(serviceList: List<MainServiceType>) {
        (this.adapter as? MainPagerAdapter)?.setFragmentList(
            MainServiceFragmentList.getFragmentList(serviceList)
        )
    }

    @JvmStatic
    @BindingAdapter("tabList")
    fun BadgeTabLayout.setTabList(tabList: List<MainServiceType>) {
        attachTabLayout(tabList)
    }

    @JvmStatic
    @BindingAdapter("fadeVisibility")
    fun View.setVisibilityWithFadeAnimation(
        fadeVisibility: FadeVisibility
    ) {
        if(fadeVisibility.duration == 0L) {
            this.visibility = if(fadeVisibility.isVisible) View.VISIBLE else fadeVisibility.goneOrInVisible
            return
        }

        this.visibility = View.VISIBLE
        if(fadeVisibility.isVisible) {
            alpha = 0f
            animate().alpha(1f).withEndAction {
                fadeVisibility.onAnimationEnd?.invoke()
            }.setStartDelay(100L).start()
        } else {
            alpha = 1f
            animate().alpha(0f).withEndAction {
                fadeVisibility.onAnimationEnd?.invoke()
            }.setStartDelay(100L).start()
        }
    }

    @JvmStatic
    @BindingAdapter("spannableText")
    fun TextView.setSpannableText(spannableStringBuilder: SpannableStringBuilder?) {
        spannableStringBuilder?.let {
            this.text = it
        }
    }

    @JvmStatic
    @BindingAdapter("labelType")
    fun PolicyLabelView.setLabelType(labelType: PolicyLabelView.LabelType) {
        PolicyLabelView.Label(labelType, 6.DP(context)).also {
            this.label = it
            startBlinkAnimation(it.isBlink, it.blinkDuration)
        }
    }

    @JvmStatic
    @BindingAdapter("itemList")
    fun AppCompatSpinner.setItems(items: List<String>) {
        this.adapter = ArrayAdapter(context, R.layout.item_splash_spinner_hint, items).apply {
            setDropDownViewResource(R.layout.item_splash_spinner)
        }
    }

    @JvmStatic
    @BindingAdapter("onSwitchChanged")
    fun SwitchCompat.onSwitchChanged(onSwitchChanged: (CompoundButton, Boolean) -> Unit) {
        setOnCheckedChangeListener { buttonView, isChecked ->
            onSwitchChanged(buttonView, isChecked)
        }
    }
}