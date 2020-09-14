package com.skfo763.my_data_app.ui.maintab.fragment.housing

import com.skfo763.my_data_app.component.PolicyLabelView
import com.skfo763.my_data_app.ui.maintab.data.PolicyHeader
import com.skfo763.my_data_app.ui.maintab.data.PolicyItem
import com.skfo763.my_data_app.ui.maintab.data.PolicyListItem
import com.skfo763.my_data_app.ui.maintab.data.PolicySearchBar
import com.skfo763.my_data_app.ui.maintab.fragment.base.BasePolicyCategoryViewModel
import com.skfo763.my_data_app.ui.maintab.fragment.base.IBasePolicyView

class HousingPolicyViewModel(private val view: IBasePolicyView) : BasePolicyCategoryViewModel(view) {
    override val categoryName: String
        get() = "주거지원"


}