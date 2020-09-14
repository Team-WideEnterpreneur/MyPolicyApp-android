package com.skfo763.remote.data

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("userId") val userIdx: Int = 0,
    @SerializedName("address") val address: String = "",
    @SerializedName("age") val age: Int = 0,
    @SerializedName("credit") val schoolCredit: String = "",
    @SerializedName("gender") val gender: String = "",
    @SerializedName("grade") val grade: String = "",
    @SerializedName("hlthInsu") val insuraceFare: String = "",
    @SerializedName("income") val income: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("perStat") val familyStat: String = "",
    @SerializedName("usrStat") val incomeStat: String = "",
    @SerializedName("wrkStat") val employmentState: String = ""
)