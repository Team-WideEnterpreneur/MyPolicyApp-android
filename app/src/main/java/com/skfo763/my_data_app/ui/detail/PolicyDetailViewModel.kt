package com.skfo763.my_data_app.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skfo763.my_data_app.extension.plusAssign
import com.skfo763.my_data_app.ui.detail.data.PolicyDetailInfo
import com.skfo763.my_data_app.ui.detail.data.PolicyDetailTabData
import com.skfo763.my_data_app.ui.detail.data.PolicyRequirement
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class PolicyDetailViewModel(private val view: IPolicyDetailView) : ViewModel() {

    class Factory(private val iView: IPolicyDetailView) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PolicyDetailViewModel(iView) as T
        }
    }

    val compositeDisposable = CompositeDisposable()

    private val _infoTabList = MutableLiveData<List<PolicyDetailTabData>>(listOf())
    private val _userScore = MutableLiveData<String>()
    private val _matchingRate = MutableLiveData<String>()
    private val _registerUrl = MutableLiveData<String>()
    private val _alertDialogTitle = MutableLiveData<String>()

    val infoTabList: LiveData<List<PolicyDetailTabData>> get() = _infoTabList
    val userScore: LiveData<String> get() = _userScore
    val matchingRate: LiveData<String> get() = _matchingRate
    val registerUrl: LiveData<String> get() = _registerUrl
    val alertDialogTitle: LiveData<String> get() = _alertDialogTitle

    fun initTabData() {
        compositeDisposable += Single.timer(100L, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _infoTabList.value = listOf(
                    PolicyDetailInfo(),
                    PolicyRequirement()
                )
            }) {
                it.printStackTrace()
            }
    }

    fun getPolicyMatchingInfo() {
        compositeDisposable += Single.timer(100L, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _matchingRate.value = String.format("%.1f", Math.random() * 100)
                _userScore.value = String.format("%.1f", Math.random() * 5)
            }) {
                it.printStackTrace()
            }

    }

    fun onRegisterButtonClicked() {
        _registerUrl.value = "https://www.youthcenter.go.kr/seekActvSptfndAppl/aboutThis.do"
    }

    fun onNotifySetButtonClicked() {
        _alertDialogTitle.value = "찜 설정이 완료되었습니다. 이제 정책 신청과 관련된 푸시 알림을 받아보실 수 있습니다!"
    }

}