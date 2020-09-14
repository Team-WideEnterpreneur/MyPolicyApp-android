package com.skfo763.my_data_app.ui.mydata

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skfo763.my_data_app.extension.plusAssign
import com.skfo763.my_data_app.getRandomLabelType
import com.skfo763.my_data_app.ui.mydata.data.*
import com.skfo763.repository.PolicyRepository
import com.skfo763.repository.UserRepository
import com.skfo763.repository.data.PolicyData
import com.skfo763.repository.data.UserData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class MyDataViewModel(
    private val view: IMyDataView,
    private val userRepository: UserRepository,
    private val policyRepository: PolicyRepository
) : ViewModel() {

    companion object {
        const val FILE_TYPE = "application/excel"
    }

    class Factory(private val iView: IMyDataView) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MyDataViewModel(
                iView,
                UserRepository(iView.xlsStorageManager),
                PolicyRepository(iView.xlsStorageManager)
            ) as T
        }
    }

    val compositeDisposable = CompositeDisposable()

    private val _recyclerItemList = MutableLiveData<MutableList<MyDataListItem>>()
    private val _myInfoData = MutableLiveData<UserData>()
    private val _policyInfoData = MutableLiveData<PolicyData>()
    private val _fileUri = MutableLiveData<Pair<Uri?, String>>()

    val recyclerItemList: LiveData<MutableList<MyDataListItem>> = _recyclerItemList
    val myInfoData: LiveData<UserData> = _myInfoData
    val policyInfoData: LiveData<PolicyData> = _policyInfoData
    val fileUri: LiveData<Pair<Uri?, String>> = _fileUri

    fun loadMyData() {
        compositeDisposable += policyRepository.getPolicySummaryList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _recyclerItemList.value = getRecyclerData(it)
            }) {
                it.printStackTrace()
            }
    }

    private fun getRecyclerData(list: List<PolicyData>): MutableList<MyDataListItem> {
        return mutableListOf<MyDataListItem>().apply {
            add(MyDataHeader("서창연", onDownloadBtnClicked = { onDownloadUserInfoButtonClicked(it) }))
            add(MyDataTitle())
            addAll(list.map {
                MyDataItem(it.category, it.policyName, getRandomLabelType(), it,
                    { item -> onDownloadPolicyInfoButtonClicked(item) }
                )
            })
            add(MyDataFooter())
        }
    }

    private fun onDownloadUserInfoButtonClicked(item: MyDataHeader) {
        _myInfoData.postValue(item.myInfoData)
    }
    
    private fun onDownloadPolicyInfoButtonClicked(item: MyDataItem) {
        _policyInfoData.postValue(item.item)
    }

    fun saveUserXls(myInfoData: UserData) {
        compositeDisposable += userRepository.saveXls(myInfoData)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _fileUri.value = it to FILE_TYPE
            }) {
                it.printStackTrace()
            }
    }

    fun saveUserCsv(myInfoData: UserData) {

    }

    fun saveUserPdf(myInfoData: UserData) {

    }


    fun savePolicyXls(policyData: PolicyData) {
        compositeDisposable += policyRepository.saveXls(policyData)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _fileUri.value = it to FILE_TYPE
            }) {
                it.printStackTrace()
            }
    }

    fun savePolicyCsv(policyData: PolicyData) {

    }

    fun savePolicyPdf(policyData: PolicyData) {

    }
}