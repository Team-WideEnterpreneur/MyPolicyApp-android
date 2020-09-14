package com.skfo763.repository.data

import com.skfo763.storage.csv.converter.CsvMyInfoConverter
import com.skfo763.storage.csv.CsvMyInfoData
import com.skfo763.storage.pdf.converter.PdfMyInfoConverter
import com.skfo763.storage.pdf.PdfMyInfoData
import com.skfo763.storage.xls.converter.XlsMyInfoConverter
import com.skfo763.storage.xls.XlsMyInfoData

data class UserData (
    val userIdx: Int,
    val address: String,
    val age: Int,
    val schoolCredit: String,
    val gender: String,
    val grade: String,
    val insuraceFare: String,
    val income: String,
    val name: String,
    val familyStat: String,
    val incomeStat: String,
    val employmentState: String
) : XlsMyInfoConverter, CsvMyInfoConverter, PdfMyInfoConverter {

    override fun getMyInfoXlsData() = XlsMyInfoData(
        this.name,
        this.age.toString() + "세",
        this.address,
        this.gender,
        this.schoolCredit,
        this.grade,
        this.insuraceFare,
        this.familyStat,
        this.incomeStat,
        this.employmentState
    )

    override fun getMyInfoCsvData() = CsvMyInfoData()

    override fun getMyInfoPdfData() = PdfMyInfoData()

}