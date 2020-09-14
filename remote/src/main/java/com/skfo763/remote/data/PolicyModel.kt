package com.skfo763.remote.data

import com.google.gson.annotations.SerializedName

data class PolicyModel (
    @SerializedName("policyId") val policyIdx: Int = 0,
    @SerializedName("poliName") val policyName: String = "",
    @SerializedName("busiType") val category: String = "",
    @SerializedName("credit") val requireSchoolCredit: String = "",
    @SerializedName("endDay") val endDueDate: String = "",
    @SerializedName("grade") val requireGrade: String = "",
    @SerializedName("income") val requireIncome: String = "",
    @SerializedName("isHvHm") val homeOwnerState: String = "",
    @SerializedName("maxAge") val requireMaxAge: Int = 0,
    @SerializedName("minAge") val requireMinAge: Int = 0,
    @SerializedName("perStat") val requireFamilyStat: String = "",
    @SerializedName("poliUrl") val policyUrl: String = "",
    @SerializedName("startDay") val startDueDate: String = "",
    @SerializedName("userMid1") val firstUserMid: String = "",
    @SerializedName("userMid2") val secondUserMid: String = "",
    @SerializedName("wrkPeMax") val requireWorkDayMax: String = "",
    @SerializedName("wrkPeMin") val requireWorkDayMin: String = "",
    @SerializedName("wrkState") val employmentState: String = ""
)