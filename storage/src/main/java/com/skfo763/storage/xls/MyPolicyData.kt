package com.skfo763.storage.xls

import com.skfo763.data_converter_annotation.XlsColumnInfo
import com.skfo763.data_converter_annotation.XlsEntity

@XlsEntity("내 정책 데이터")
class MyPolicyData {

    @XlsColumnInfo("머리") val head = "Head"

    @XlsColumnInfo("가슴") val chest = "chest"

    @XlsColumnInfo("다리") val leg = "leg"

}