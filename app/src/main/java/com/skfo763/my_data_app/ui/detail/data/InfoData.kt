package com.skfo763.my_data_app.ui.detail.data

sealed class PolicyDetailTabData(
    open val title: String
)

data class PolicyDetailInfo(
    val message: String = "h1",
    val category: String = "취업지원, 교육훈련, 체험, 인턴",
    val supports: String = "월 50만원 x 6개월, 지원 중 취업 시 지원 중단" +
            "\n -> 취업 후 3개월 근속 시 취업성공금 지급\n\n" +
            "<취업성공금 제도>" +
            "\n -(목적) 지원금으로 인한 청년들의 노동 시장 진입 지연을 방지하기 위함" +
            "\n -(내용) 1. 지원금 수급 중 취업, 2. 취업 후 3개월 근속 시 지급",
    val volume: String = "2020년 약 10만명 지원 예정",
    val dueDate: String = "매월 1일~25일까지 접수/신청"
): PolicyDetailTabData("상세 내용")

data class PolicyRequirement(
    val message: String = "h2"
): PolicyDetailTabData("지원 요건")