package com.skfo763.my_data_app.ui.maintab.fragment.job

import com.skfo763.my_data_app.ui.maintab.fragment.base.BasePolicyCategoryViewModel
import com.skfo763.my_data_app.ui.maintab.fragment.base.IBasePolicyView

class JobPolicyViewModel(private val view: IBasePolicyView) : BasePolicyCategoryViewModel(view) {
    override val categoryName: String
        get() = "취업지원"
}