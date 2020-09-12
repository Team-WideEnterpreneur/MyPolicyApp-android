package com.skfo763.my_data_app.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skfo763.my_data_app.R

class PolicyDetailViewModel(private val view: IPolicyDetailView) : ViewModel() {

    class Factory(private val iView: IPolicyDetailView) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PolicyDetailViewModel(iView) as T
        }
    }

    private val _infoTabList = MutableLiveData(view.getArrayListRes(R.array.policy_info_tab_list))


    val infoTabList: LiveData<List<String>> get() = _infoTabList
}