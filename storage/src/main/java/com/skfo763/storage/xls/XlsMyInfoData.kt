package com.skfo763.storage.xls

import com.skfo763.data_converter_annotation.XlsColumnInfo
import com.skfo763.data_converter_annotation.XlsEntity

@XlsEntity("마이 데이터 - 개인정보")
class XlsMyInfoData(
        @XlsColumnInfo("이름") val name: String,
        @XlsColumnInfo("나이") val age: String,
        @XlsColumnInfo("성별") val gender: String,
        @XlsColumnInfo("취업 여부") val jobState: String,
        @XlsColumnInfo("재직 직장명") val currentJobName: String,
        @XlsColumnInfo("소득 분위") val incomeState: String
)