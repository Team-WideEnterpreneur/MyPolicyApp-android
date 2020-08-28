package com.skfo763.my_data_app.ui.splash

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.view.View
import android.view.View.GONE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skfo763.my_data_app.commondata.FadeVisibility
import com.skfo763.my_data_app.ext.setColorSpan
import com.skfo763.my_data_app.ext.setSizeSpan
import com.skfo763.my_data_app.extension.log
import com.skfo763.my_data_app.extension.plusAssign
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class SplashViewModel(private val view: ISplashView): ViewModel() {
    
    private val compositeDisposable = CompositeDisposable()

    private val _serviceCountText = MutableLiveData("당신을 위한 %d개의 정책 정보!")
    private val _suppressLayout = MutableLiveData(false)
    private val _splashVisibility = MutableLiveData(FadeVisibility(true, 0L, GONE))
    private val _inputVisibility = MutableLiveData(FadeVisibility(false, 0L, GONE))
    private val _inputPersonalTitle = MutableLiveData<SpannableStringBuilder>()
    private val _inputOptionalTitle = MutableLiveData<SpannableStringBuilder>()

    val serviceCountText: LiveData<String> = _serviceCountText
    val suppressLayout: LiveData<Boolean> = _suppressLayout
    val splashVisibility: LiveData<FadeVisibility> = _splashVisibility
    val inputVisibility: LiveData<FadeVisibility> = _inputVisibility
    val inputPersonalTitle: LiveData<SpannableStringBuilder> = _inputPersonalTitle
    val inputOptionalTitle: LiveData<SpannableStringBuilder> = _inputOptionalTitle


    fun initializeApplication() {
        compositeDisposable += Observable.interval(0, 1, TimeUnit.MILLISECONDS)
            .take(2432)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                _inputPersonalTitle.value = makeInputUserTitleSpan(
                    view.splashConstantText.userPersonalInfoTitle,
                    view.splashConstantText.mandateSubTitle
                )
                _inputOptionalTitle.value = makeInputUserTitleSpan(
                    view.splashConstantText.userOptionalInfoTitle,
                    view.splashConstantText.optionalSubTitle
                )
                _suppressLayout.value = true
                _splashVisibility.value = FadeVisibility(false, 500L, GONE) {
                    startUserInputLayout()
                }
            }
            .subscribe({
                _serviceCountText.value = String.format(view.splashConstantText.serviceCountInfoText, it.toInt())
            }) {
                log(it.message)
            }
    }

    private fun makeInputUserTitleSpan(
        title: String,
        subtitle: String
    ) = SpannableStringBuilder(title + subtitle).apply {
        val titlePoint = 0 to title.length - 1
        val subtitlePoint = title.length to title.length + subtitle.length - 1

        setColorSpan(Color.parseColor("#ffffff"), titlePoint.first, titlePoint.second)
        setColorSpan(Color.parseColor("#ffffff"), subtitlePoint.first, subtitlePoint.second)

        setSizeSpan(0.8f, subtitlePoint.first, subtitlePoint.second)
    }


    private fun startUserInputLayout() {
        _inputVisibility.value = FadeVisibility(true, 500L, GONE) {
            _suppressLayout.value = false
        }
    }

    fun onCompleteButtonClicked() {
        view.moveOnToMainActivity()
    }
}