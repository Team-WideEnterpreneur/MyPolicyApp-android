package com.skfo763.my_data_app.component

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.databinding.ViewCheckboxTextBinding
import com.skfo763.my_data_app.ui.splash.SplashActivity

class CheckBoxTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseTextView(context, attributeSet, defStyleAttr) {

    private val binding = ViewCheckboxTextBinding.inflate(
        LayoutInflater.from(context), this, true)

    private val _firstBoxChecked = MutableLiveData(true)
    private val _secondBoxChecked = MutableLiveData(false)

    val firstBoxChecked: LiveData<Boolean> = _firstBoxChecked
    val secondBoxChecked: LiveData<Boolean> = _secondBoxChecked

    var showBadge: Boolean = false
        set(value) {
            binding.checkBoxMoreInfoBadge.isGone = !showBadge
            field = value
        }

    var url: String = ""

    var firstItemText: String = ""
        set(value) {
            binding.appCompatCheckBox.text = value
            field = value
        }

    var secondItemText: String = ""
        set(value) {
            binding.appCompatCheckBox2.text = value
            field = value
        }

    init {
        binding.view = this
        binding.lifecycleOwner = context as AppCompatActivity
        binding.checkBoxTextTitle.text = titleText
        val typedArray = context.theme.obtainStyledAttributes(
            attributeSet, R.styleable.CheckBoxTextView, 0, 0)
        try {
            showBadge = typedArray.getBoolean(R.styleable.CheckBoxTextView_tv_show_badge, false)
            url = typedArray.getString(R.styleable.CheckBoxTextView_tv_url) ?: ""
            firstItemText = typedArray.getString(R.styleable.CheckBoxTextView_tv_first_item_text) ?: ""
            secondItemText = typedArray.getString(R.styleable.CheckBoxTextView_tv_second_item_text) ?: ""
        } finally {
            typedArray.recycle()
        }
    }

    fun onTitleClicked() {
        if(!showBadge || url.isEmpty()) return
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    fun onFirstCheckBoxClicked() {
        if(_firstBoxChecked.value == false) {
            _firstBoxChecked.value = true
            _secondBoxChecked.value = false
        }
    }

    fun onSecondCheckBoxClicked() {
        if(_secondBoxChecked.value == false) {
            _secondBoxChecked.value = true
            _firstBoxChecked.value = false
        }
    }
}