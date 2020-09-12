package com.skfo763.my_data_app.ui.mydata

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skfo763.my_data_app.extension.plusAssign
import com.skfo763.my_data_app.ui.mydata.data.*
import com.skfo763.storage.pdf.PdfMyInfoData
import com.skfo763.storage.xls.XlsMyInfoData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class MyDataViewModel(private val view: IMyDataView) : ViewModel() {

    class Factory(private val iView: IMyDataView) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MyDataViewModel(iView) as T
        }
    }

    val compositeDisposable = CompositeDisposable()

    private val _recyclerItemList = MutableLiveData<MutableList<MyDataListItem>>()
    private val _myInfoData = MutableLiveData<MyInfoData>()
    private val _fileUri = MutableLiveData<Pair<Uri?, String>>()

    val recyclerItemList: LiveData<MutableList<MyDataListItem>> = _recyclerItemList
    val myInfoData: LiveData<MyInfoData> = _myInfoData
    val fileUri: LiveData<Pair<Uri?, String>> = _fileUri

    fun loadMyData() {
        compositeDisposable += Single.timer(200L, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _recyclerItemList.value = mutableListOf(
                    MyDataHeader(onDownloadBtnClicked = this::onDownloadUserInfoButtonClicked),
                    MyDataTitle(),
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
        _myInfoData.postValue(item.myInfoData)
    }

    fun saveXls(myInfoXlsData: XlsMyInfoData) {
        view.xlsStorageManager.apply{
            if(!checkFileExists("마이 데이터 - 내 정보")) {
                setSheet("내 정보")
                setRow(listOf(myInfoXlsData), "내 정보")
                saveExcel("마이 데이터 - 내 정보")
            }
            _fileUri.postValue(this.getUrl() to "application/excel")
        }
    }

    fun saveCsv(myInfoXlsData: XlsMyInfoData) {


    }

    fun savePdf(myInfoPdfData: PdfMyInfoData) {

    }


}