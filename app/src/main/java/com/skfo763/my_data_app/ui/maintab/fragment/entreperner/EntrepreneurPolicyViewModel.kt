package com.skfo763.my_data_app.ui.maintab.fragment.entreperner

import com.skfo763.my_data_app.ui.maintab.fragment.base.BasePolicyCategoryViewModel
import com.skfo763.my_data_app.ui.maintab.fragment.base.IBasePolicyView

class EntrepreneurPolicyViewModel(private val view: IBasePolicyView) : BasePolicyCategoryViewModel(view) {
    override val categoryName: String
        get() = "창업지원"

}