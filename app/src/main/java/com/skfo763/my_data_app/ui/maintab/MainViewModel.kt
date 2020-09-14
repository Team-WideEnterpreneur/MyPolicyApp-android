package com.skfo763.my_data_app.ui.maintab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skfo763.my_data_app.extension.plusAssign
import com.skfo763.my_data_app.ui.maintab.data.MainServiceType
import com.skfo763.repository.PolicyRepository
import com.skfo763.repository.UserRepository
import com.skfo763.storage.xls.XlsStorageManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {

    class Factory: ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            MainViewModel() as T
    }

    private val compositeDisposable = CompositeDisposable()

    private val _isLoading = MutableLiveData<Boolean>()
    private val _serviceList = MutableLiveData<List<MainServiceType>>(listOf())

    val isLoading: LiveData<Boolean> = _isLoading
    val serviceList: LiveData<List<MainServiceType>> = _serviceList
    var openMyData: Boolean = false

    fun getServiceList() {
        _isLoading.postValue(true)

        compositeDisposable += Single.timer(200L, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate {
                _isLoading.value = false
            }.subscribe({
                _serviceList.value = listOf(
                    MainServiceType("all", "전체", false),
                    MainServiceType("job", "취업지원", true),
                    MainServiceType("entrepreneurship", "창업지원"),
                    MainServiceType("life", "생활복지", true),
                    MainServiceType("housing", "주거금융", true),
                    MainServiceType("participants", "정책참여"),
                    MainServiceType("covid19", "(특별)코로나19", false)
                )
            }) {
                it.printStackTrace()
            }
    }

    fun goMyButtonClicked() {

    }
}