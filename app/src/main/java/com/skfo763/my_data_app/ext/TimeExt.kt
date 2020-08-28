package com.skfo763.my_data_app.componenty

import com.skfo763.my_data_app.commondata.SimpleDate
import java.time.LocalTime
import java.util.*

fun Long.toSimpleDate(): SimpleDate {
    val now = Calendar.getInstance()
    return SimpleDate(
        now.get(Calendar.YEAR),
        now.get(Calendar.MONTH) + 1,
        now.get(Calendar.DAY_OF_MONTH)
    )
}