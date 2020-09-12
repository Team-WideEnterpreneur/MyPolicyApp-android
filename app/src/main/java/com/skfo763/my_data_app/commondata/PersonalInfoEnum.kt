package com.skfo763.my_data_app.commondata

enum class GenderEnum(val genderString: String) {
    MAN("남자"),
    WOMAN("여자"),
    UNKNOWN("무응답")
}

enum class JobState(val jobStateString: String) {
    EMPLOYED("재직중"),
    UNEMPLOYED("무직"),
    UNKNOWN("무응답")
}

enum class IncomeState(val incomeStateString: String) {
    FIRST_QUARTILE("1분위"),
    SECOND_QUARTILE("2분위"),
    THIRD_QUARTILE("3분위"),
    FOURTH_QUARTILE("4분위"),
    FIFTH_QUARTILE("5분위"),
    SIXTH_QUARTILE("6분위"),
    SEVENTH_QUARTILE("7분위"),
    EIGHTH_QUARTILE("8분위"),
    NINTH_QUARTILE("9분위"),
    TENTH_QUARTILE("10분위")
}