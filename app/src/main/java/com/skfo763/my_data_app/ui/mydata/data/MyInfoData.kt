package com.skfo763.my_data_app.ui.mydata.data

import com.skfo763.my_data_app.commondata.GenderEnum
import com.skfo763.my_data_app.commondata.IncomeState
import com.skfo763.my_data_app.commondata.JobState
import com.skfo763.storage.csv.CsvMyInfoConverter
import com.skfo763.storage.csv.CsvMyInfoData
import com.skfo763.storage.pdf.PdfMyInfoConverter
import com.skfo763.storage.pdf.PdfMyInfoData
import com.skfo763.storage.xls.XlsMyInfoData
import com.skfo763.storage.xls.XlsMyInfoConverter

data class MyInfoData(
    val name: String,
    val age: Int,
    val gender: GenderEnum,
    val jobState: JobState,
    val currentJobName: String?,
    val incomeState: IncomeState
): XlsMyInfoConverter, CsvMyInfoConverter, PdfMyInfoConverter {

    override fun getMyInfoXlsData() = XlsMyInfoData(
        this.name,
        this.age.toString(),
        this.gender.genderString,
        this.jobState.jobStateString,
        this.currentJobName ?: "없음",
        incomeState.incomeStateString
    )

    override fun getMyInfoCsvData() = CsvMyInfoData()

    override fun getMyInfoPdfData() = PdfMyInfoData()

}