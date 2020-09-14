package com.skfo763.repository.data

import com.skfo763.storage.csv.CsvPolicyInfoData
import com.skfo763.storage.csv.converter.CsvPolicyInfoConverter
import com.skfo763.storage.pdf.PdfPolicyInfoData
import com.skfo763.storage.pdf.converter.PdfPolicyInfoConverter
import com.skfo763.storage.xls.XlsPolicyInfoData
import com.skfo763.storage.xls.converter.XlsPolicyInfoConverter

data class PolicyData (
    val policyIdx: Int = 0,
    val policyName: String = "",
    val category: String = "",
    val requireSchoolCredit: String = "",
    val endDueDate: String = "",
    val requireGrade: String = "",
    val requireIncome: String = "",
    val homeOwnerState: String = "",
    val requireMaxAge: Int,
    val requireMinAge: Int,
    val requireFamilyStat: String = "",
    val policyUrl: String = "",
    val startDueDate: String = "",
    val firstUserMid: String = "",
    val secondUserMid: String = "",
    val requireWorkDayMax: String = "",
    val requireWorkDayMin: String = "",
    val employmentState: String = ""
): XlsPolicyInfoConverter, CsvPolicyInfoConverter, PdfPolicyInfoConverter {

    override fun getPolicyInfoXlsData() = XlsPolicyInfoData(
        category,
        requireSchoolCredit,
        endDueDate,
        requireGrade,
        requireIncome,
        homeOwnerState,
        requireMaxAge.toString() + "세",
        requireMinAge.toString() + "세",
        requireFamilyStat,
        policyUrl,
        startDueDate,
        firstUserMid,
        secondUserMid,
        requireWorkDayMax,
        requireWorkDayMin,
        employmentState
    )

    override fun getPdfPolicyInfo() = PdfPolicyInfoData()

    override fun getCsvPolicyInfo() = CsvPolicyInfoData()
}