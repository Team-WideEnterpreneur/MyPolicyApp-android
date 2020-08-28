package com.skfo763.my_data_app.ui.splash

interface ISplashView {

    class Text(
        var serviceCountInfoText: String,
        var userPersonalInfoTitle: String,
        var userOptionalInfoTitle: String,
        var mandateSubTitle: String,
        var optionalSubTitle: String
    )

    val splashConstantText: Text

    fun moveOnToMainActivity()

}