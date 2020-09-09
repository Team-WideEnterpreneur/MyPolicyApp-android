package com.skfo763.my_data_app.ui.detail

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skfo763.storage.xls.MyPolicyData

class PolicyDetailViewModel : ViewModel() {

    val hello = MutableLiveData<List<MyPolicyData>>()

    fun onTestButtonClicked(view: View) {
        hello.value = listOf(
            MyPolicyData(),
            MyPolicyData(),
            MyPolicyData(),
            MyPolicyData(),
            MyPolicyData(),
            MyPolicyData(),
            MyPolicyData(),
            MyPolicyData(),
            MyPolicyData(),
            MyPolicyData(),
            MyPolicyData(),
            MyPolicyData(),
            MyPolicyData(),
            MyPolicyData(),
            MyPolicyData(),
            MyPolicyData(),
            MyPolicyData()
        )
    }
}