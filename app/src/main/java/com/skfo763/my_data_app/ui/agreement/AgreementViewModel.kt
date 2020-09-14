package com.skfo763.my_data_app.ui.agreement

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skfo763.my_data_app.ext.parseWithNullString
import com.skfo763.my_data_app.extension.plusAssign
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class AgreementViewModel(private val view: IAgreementView) : ViewModel() {

    class Factory(private val iView: IAgreementView): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            AgreementViewModel(iView) as T
    }

    val compositeDisposable = CompositeDisposable()

    private val _certVisibility = MutableLiveData(false)
    private val _isLoading = MutableLiveData(false)
    private val _rowBackground = MutableLiveData(view.rowBackGround.first)

    val certVisibility: LiveData<Boolean> = _certVisibility
    val isLoading: LiveData<Boolean> = _isLoading
    val rowBackGround: LiveData<Int> = _rowBackground


    fun onBrowserCertLoginClicked(buttonView: View) {
        view.url.parseWithNullString()?.let {
            view.openAgreementBrowser(it)
        }
    }

    fun onPublicCertLoginClicked(buttonView: View) {
        _isLoading.postValue(true)

        compositeDisposable += Single.timer(1000L, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate {
                _isLoading.value = false
            }.subscribe({
                _certVisibility.value = true
            }) {
                it.printStackTrace()
            }
    }

    fun onPublicCertCancelClicked(buttonView: View) {
        _certVisibility.value = false
    }

    fun onCertifyClicked(v: View) {
        _isLoading.postValue(true)

        compositeDisposable += Single.timer(1000L, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate {
                _isLoading.value = false
            }.subscribe({
                view.finishActivity()
            }) {
                it.printStackTrace()
            }
    }

    fun onRowClicked(v: View) {
        if(_rowBackground.value == view.rowBackGround.first) {
            _rowBackground.value = view.rowBackGround.second
        } else {
            _rowBackground.value = view.rowBackGround.first
        }
    }

}