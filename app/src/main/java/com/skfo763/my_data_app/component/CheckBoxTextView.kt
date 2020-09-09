package com.skfo763.my_data_app.component

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.databinding.ViewCheckboxTextBinding
import com.skfo763.my_data_app.ext.parseWithNullString
import com.skfo763.my_data_app.ext.setTextCheckEmpty

class CheckBoxTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseTextView(context, attributeSet, defStyleAttr) {

    private val binding = ViewCheckboxTextBinding.inflate(
        LayoutInflater.from(context), this, true)

    private val _firstBoxChecked = MutableLiveData(false)
    private val _secondBoxChecked = MutableLiveData(false)

    val firstBoxChecked: LiveData<Boolean> = _firstBoxChecked
    val secondBoxChecked: LiveData<Boolean> = _secondBoxChecked

    var showBadge: Boolean = false
        set(value) {
            binding.checkBoxMoreInfoBadge.isGone = !showBadge
            field = value
        }

    var infoUrl: String = ""
    var confirmUrl: String = ""

    var firstItemText: String = ""
        set(value) {
            binding.appCompatCheckBox.setTextCheckEmpty(value)
            field = value
        }

    var secondItemText: String = ""
        set(value) {
            binding.appCompatCheckBox2.setTextCheckEmpty(value)
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
            infoUrl = typedArray.getString(R.styleable.CheckBoxTextView_tv_info_url) ?: ""
            confirmUrl = typedArray.getString(R.styleable.CheckBoxTextView_tv_confirm_url) ?: ""
            firstItemText = typedArray.getString(R.styleable.CheckBoxTextView_tv_first_item_text) ?: ""
            secondItemText = typedArray.getString(R.styleable.CheckBoxTextView_tv_second_item_text) ?: ""
        } finally {
            typedArray.recycle()
        }
    }

    fun onTitleClicked() {
        if(!showBadge || infoUrl.isEmpty()) return
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(infoUrl)))
    }

    fun onFirstCheckBoxClicked() {
        if(_firstBoxChecked.value == true) {
            onCancel(this::switchToSecond)
        } else {
            confirmUrl.parseWithNullString()?.let {
                context.startActivity(Intent(Intent.ACTION_VIEW, it))
                switchToFirst()
            } ?: kotlin.run {
                switchToFirst()
            }
        }
    }

    fun onSecondCheckBoxClicked() {
        if(_secondBoxChecked.value == true) {
            onCancel(this::switchToFirst)
        } else {
            switchToSecond()
        }
    }

    private fun onCancel(doAtWithoutConfirm: () -> Unit) {
        if(confirmUrl.isNotEmpty()) {
            AlertDialog.Builder(context)
                .setTitle("동의 알림")
                .setMessage("지금 동의를 취소하시면, 재인증 절차를 거쳐야 재동의하실 수 있습니다. 그래도 동의를 취소하시겠습니까?")
                .setPositiveButton("예") { dialog, _ ->
                    doAtWithoutConfirm()
                    dialog.dismiss()
                }.show()
        } else {
            doAtWithoutConfirm()
        }
    }

    private fun switchToFirst() {
        _firstBoxChecked.value = true
        _secondBoxChecked.value = false
    }

    private fun switchToSecond() {
        _firstBoxChecked.value = false
        _secondBoxChecked.value = true
    }
}