package com.skfo763.my_data_app.ui.mydata.data

import com.skfo763.my_data_app.commondata.GenderEnum
import com.skfo763.my_data_app.commondata.IncomeState
import com.skfo763.my_data_app.commondata.JobState

data class MyInfoData(
    val name: String,
    val age: Int,
    val gender: GenderEnum,
    val jobState: JobState,
    val currentJobName: String?,
    val incomeState: IncomeState
)