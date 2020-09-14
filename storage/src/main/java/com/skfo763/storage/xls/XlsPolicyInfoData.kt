package com.skfo763.storage.xls

import com.skfo763.data_converter_annotation.XlsColumnInfo
import com.skfo763.data_converter_annotation.XlsEntity

@XlsEntity("마이 데이터 - 정책 정보")
class XlsPolicyInfoData(
    @XlsColumnInfo("사업 분야") val category: String,
    @XlsColumnInfo("이수 학점") val credit: String,
    @XlsColumnInfo("신청 만료일") val endDueDate: String,
    @XlsColumnInfo("성적") val grade: String,
    @XlsColumnInfo("신고 소득") val income: String,
    @XlsColumnInfo("주택 소유 여부") val havingHome: String,
    @XlsColumnInfo("최대 나이 제한") val maxAge: String,
    @XlsColumnInfo("최소 나이 제한") val minAge: String,
    @XlsColumnInfo("다자녀, 기초&차상위 여부") val perStat: String,
    @XlsColumnInfo("신청 URL") val policyUrl: String,
    @XlsColumnInfo("가구 소득 기준 중위소득 1") val firstUserMid: String,
    @XlsColumnInfo("소득 분위") val incomeState: String,
    @XlsColumnInfo("최대 재직 기간") val maxWorkDay: String,
    @XlsColumnInfo("최소 재직 기간") val minWorkDay: String,
    @XlsColumnInfo("재직 기업 규모") val workScale: String,
    @XlsColumnInfo("재학&재직 상태") val employmentStatus: String


)