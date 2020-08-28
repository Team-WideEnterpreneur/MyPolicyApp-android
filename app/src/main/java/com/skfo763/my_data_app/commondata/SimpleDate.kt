package com.skfo763.my_data_app.commondata

class SimpleDate (
    var year: Int,
    var month: Int,
    var day: Int
) {
    companion object {
        const val DATE_FORMAT_TEXT = "%d년 %d월 %d일"
    }

    val dateString get() = String.format(DATE_FORMAT_TEXT, year, month, day)
}