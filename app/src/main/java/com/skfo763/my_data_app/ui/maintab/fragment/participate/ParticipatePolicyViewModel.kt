package com.skfo763.my_data_app.ui.maintab.fragment.participate

import com.skfo763.my_data_app.ui.maintab.fragment.base.BasePolicyCategoryViewModel
import com.skfo763.my_data_app.ui.maintab.fragment.base.IBasePolicyView

class ParticipatePolicyViewModel(private val view: IBasePolicyView) : BasePolicyCategoryViewModel(view) {
    override val categoryName: String
        get() = "정책참여"


}