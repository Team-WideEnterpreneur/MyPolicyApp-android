package com.skfo763.repository

import android.net.Uri
import com.skfo763.remote.RetroBuilder
import com.skfo763.remote.api.PolicyRxApi
import com.skfo763.repository.converter.PolicyConverter
import com.skfo763.repository.data.PolicyData
import com.skfo763.storage.xls.XlsStorageManager
import io.reactivex.Single

class PolicyRepository (
    private val xlsStorageManager: XlsStorageManager,
    private val api: PolicyRxApi = RetroBuilder().getRetrofit().create(PolicyRxApi::class.java),
) {

    private val policyConverter = PolicyConverter()

    fun getPolicySummaryList() = api.getPolicySummaryList().map {
        it.map { item -> policyConverter.convertModelToPolicyData(item) }
    }

    fun getPolicyDetail(policyIdx: Int) = api.getPolicyData(policyIdx).map {
        policyConverter.convertModelToPolicyData(it)
    }

    fun saveXls(data: PolicyData) = Single.create<Uri?>{
        xlsStorageManager.apply{
            if(!checkFileExists("마이 데이터 - 정책 정보")) {
                setSheet("정책 정보")
                setPolicyInfoRow(listOf(data.getPolicyInfoXlsData()), "정책 정보")
                saveExcel("마이 데이터 - 정책 정보")
            }
            getUrl()?.let { url ->
                it.onSuccess(url)
            } ?: kotlin.run {
                it.onError(IllegalStateException())
            }
        }
    }
}