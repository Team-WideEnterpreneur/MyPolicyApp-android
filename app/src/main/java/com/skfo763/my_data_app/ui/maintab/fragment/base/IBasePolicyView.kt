package com.skfo763.my_data_app.ui.maintab.fragment.base

import android.graphics.drawable.Drawable
import com.skfo763.my_data_app.ui.maintab.data.PolicyItem
import com.skfo763.storage.xls.XlsStorageManager

interface IBasePolicyView {
    fun moveOnToPolicyDetailActivity(policyData: PolicyItem)

    val headerBackgroundDrawable: Drawable

    val searchSeedList: List<String>

    val xlsStorageManager: XlsStorageManager

}