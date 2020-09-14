package com.skfo763.my_data_app.ui.maintab.fragment.life

import com.skfo763.my_data_app.ui.maintab.fragment.base.BasePolicyCategoryViewModel
import com.skfo763.my_data_app.ui.maintab.fragment.base.IBasePolicyView

class Covid19PolicyViewModel(private val view: IBasePolicyView) : BasePolicyCategoryViewModel(view) {

    override val categoryName: String get() = "코로나19"

}