package com.skfo763.my_data_app.component

import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skfo763.my_data_app.R
import com.skfo763.my_data_app.commondata.SimpleDate
import com.skfo763.my_data_app.componenty.toSimpleDate
import com.skfo763.my_data_app.databinding.ViewCalendarTextBinding

class CalendarTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseTextView(context, attributeSet, defStyleAttr) {

    private val binding = ViewCalendarTextBinding.inflate(
        LayoutInflater.from(context), this, true)

    init {
        binding.calendarTextTitle.text = titleText
    }
    
    var calendarText: String = ""
        set(value) {
            binding.appCompatTextView3.text = value
            field = value
        }

    private val _selectedDate = MutableLiveData<SimpleDate>()
    private val _selectedDateString = MutableLiveData(titleText)

    val selectedDate: LiveData<SimpleDate> = _selectedDate
    val selectedDateString: LiveData<String> = _selectedDateString

    private val listener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        val simpleDate = SimpleDate(year, month, dayOfMonth)
        _selectedDate.value = simpleDate
        _selectedDateString.value = simpleDate.dateString
        datePickerDialog.dismiss()
    }

    private val datePickerDialog: DatePickerDialog by lazy {
        val simpleDate = System.currentTimeMillis().toSimpleDate()
        DatePickerDialog(context, listener, simpleDate.year, simpleDate.month, simpleDate.day)
    }

    init {
        binding.view = this
        binding.lifecycleOwner = context as AppCompatActivity
        val typedArray = context.theme.obtainStyledAttributes(
            attributeSet, R.styleable.CalendarTextView, 0, 0)
        try {
            calendarText = typedArray.getString(R.styleable.CalendarTextView_calendar_text) ?: ""
        } finally {
            typedArray.recycle()
        }
    }

    fun onCalendarButtonClicked() {
        datePickerDialog.show()
    }
}