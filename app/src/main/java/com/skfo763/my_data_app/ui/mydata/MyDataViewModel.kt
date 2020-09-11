package com.skfo763.my_data_app.ui.mydata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skfo763.my_data_app.extension.plusAssign
import com.skfo763.my_data_app.ui.mydata.data.MyDataFooter
import com.skfo763.my_data_app.ui.mydata.data.MyDataHeader
import com.skfo763.my_data_app.ui.mydata.data.MyDataItem
import com.skfo763.my_data_app.ui.mydata.data.MyDataListItem
import com.skfo763.storage.DownloadDataFormat
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import java.util.concurrent.TimeUnit

class MyDataViewModel(view: IMyDataView) : ViewModel() {

    class Factory(private val iView: IMyDataView) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MyDataViewModel(iView) as T
        }
    }

    val compositeDisposable = CompositeDisposable()

    private val _recyclerItemList = MutableLiveData<MutableList<MyDataListItem>>()
    private val _downloadChoiceDialog: MutableLiveData<DownloadDataFormat>()

    val recyclerItemList: LiveData<MutableList<MyDataListItem>> = _recyclerItemList

    fun loadMyData() {
        compositeDisposable += Single.timer(200L, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _recyclerItemList.value = mutableListOf(
                    MyDataHeader(onDownloadBtnClicked = this::onDownloadUserInfoButtonClicked),
                    MyDataItem(),
                    MyDataItem(),
                    MyDataItem(),
                    MyDataItem(),
                    MyDataItem(),
                    MyDataItem(),
                    MyDataItem(),
                    MyDataItem(),
                    MyDataItem(),
                    MyDataItem(),
                    MyDataItem(),
                    MyDataItem(),
                    MyDataItem(),
                    MyDataItem(),
                    MyDataItem(),
                    MyDataItem(),
                    MyDataFooter()
                )
            }) {
                it.printStackTrace()
            }
    }



    private fun onDownloadUserInfoButtonClicked(item: MyDataHeader) {
        _downloadChoiceDialog.postValue()
    }

}