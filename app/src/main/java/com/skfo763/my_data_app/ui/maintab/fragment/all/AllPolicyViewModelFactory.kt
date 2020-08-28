package com.skfo763.my_data_app.ui.maintab.fragment.all

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skfo763.my_data_app.ui.maintab.fragment.base.IBasePolicyView

class AllPolicyViewModelFactory(private val iView: IBasePolicyView) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AllPolicyViewModel(iView) as T
    }
}