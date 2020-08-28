package com.skfo763.my_data_app.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.databinding.ViewSpinnerTextBinding
import com.skfo763.my_data_app.utils.BindingAdapter.setItems

class SpinnerTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseTextView(context, attributeSet, defStyleAttr), AdapterView.OnItemSelectedListener {

    private val binding = ViewSpinnerTextBinding.inflate(
        LayoutInflater.from(context), this, true)

    private val _selectedEducatedState = MutableLiveData<String>()
    val selectedEducatedState: String? = _selectedEducatedState.value

    init {
        binding.view = this
        binding.lifecycleOwner = context as AppCompatActivity
        binding.spinnerTextTitle.text = titleText
        binding.spinnerTextSpinner.setItems(resources.getStringArray(R.array.educated_state).toList())
        binding.spinnerTextSpinner.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        _selectedEducatedState.value = binding.spinnerTextSpinner.getItemAtPosition(position) as String
    }

    override fun onNothingSelected(parent: AdapterView<*>?) = Unit
}