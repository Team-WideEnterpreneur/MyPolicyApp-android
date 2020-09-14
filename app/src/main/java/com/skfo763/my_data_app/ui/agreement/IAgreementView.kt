package com.skfo763.my_data_app.ui.agreement

import android.net.Uri

interface IAgreementView {

    val url: String

    val rowBackGround: Pair<Int, Int>

    fun openAgreementBrowser(webUri: Uri)

    fun finishActivity()

}