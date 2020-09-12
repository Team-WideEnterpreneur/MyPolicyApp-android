package com.skfo763.my_data_app.ui.detail.data

sealed class PolicyDetailTabData

data class PolicyDetailInfo(
    val title: String
): PolicyDetailTabData()

data class PolicyRequirement(
    val title: String
): PolicyDetailTabData()