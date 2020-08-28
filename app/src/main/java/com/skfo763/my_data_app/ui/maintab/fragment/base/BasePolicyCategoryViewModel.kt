package com.skfo763.my_data_app.ui.maintab.fragment.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skfo763.my_data_app.extension.plusAssign
import com.skfo763.my_data_app.ui.maintab.data.PolicyItem
import com.skfo763.my_data_app.ui.maintab.data.PolicyListItem
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

abstract class BasePolicyCategoryViewModel(private val view: IBasePolicyView): ViewModel() {

    abstract val tempRecyclerItemList: MutableList<PolicyListItem>

    private val compositeDisposable = CompositeDisposable()

    private val _recyclerItemList = MutableLiveData(mutableListOf<PolicyListItem>())

    val recyclerItemList: LiveData<MutableList<PolicyListItem>> = _recyclerItemList

    fun getPolicyListData() {
        compositeDisposable += Single.timer(500L, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _recyclerItemList.value = tempRecyclerItemList
            }) {
                it.printStackTrace()
            }
    }

    fun onRecyclerViewItemClicked(policyData: PolicyItem) {
        view.moveOnToPolicyDetailActivity(policyData)
    }
}