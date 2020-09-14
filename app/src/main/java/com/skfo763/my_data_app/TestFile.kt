package com.skfo763.my_data_app

import com.skfo763.my_data_app.component.PolicyLabelView
import com.skfo763.repository.data.UserData
import kotlin.random.Random

fun makeUserData() = UserData(
    0,
    "서울특별시 송파구 오금로 11길 43(잠실 트리움) 306호",
    23,
    "104/130",
    "남자",
    "3.6/4.5",
    "23124원/월",
    "2624700(세후)",
    "서창연",
    "2인 가구",
    "산정 기록 없음",
    "재직 중"
)

val labelArray = arrayOf(
    PolicyLabelView.LabelType.TYPE_EXPIRED,
    PolicyLabelView.LabelType.TYPE_ON_AIR,
    PolicyLabelView.LabelType.TYPE_WAITING,
)

fun getRandomLabelType(): PolicyLabelView.LabelType {
    val random = Random.nextInt(3)
    return labelArray[random]
}