package com.skfo763.storage.xls

import com.skfo763.data_converter_annotation.XlsColumnInfo
import com.skfo763.data_converter_annotation.XlsEntity

@XlsEntity("마이 데이터 - 개인정보")
class XlsMyInfoData(
        @XlsColumnInfo("이름") val name: String,
        @XlsColumnInfo("나이") val age: String,
        @XlsColumnInfo("주소") val address: String,
        @XlsColumnInfo("성별") val gender: String,
        @XlsColumnInfo("학점") val credit: String,
        @XlsColumnInfo("성적") val grade: String,
        @XlsColumnInfo("건강보험료") val healthInsurance: String,
        @XlsColumnInfo("다자녀, 기초&차상위 여부") val familyStatus: String,
        @XlsColumnInfo("소득 분위") val incomeState: String,
        @XlsColumnInfo("재학&재직 여부") val jobState: String
)