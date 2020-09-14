package com.skfo763.repository

import android.net.Uri
import android.util.Log
import com.skfo763.remote.RetroBuilder
import com.skfo763.remote.api.UserRxApi
import com.skfo763.repository.converter.PolicyConverter
import com.skfo763.repository.converter.UserConverter
import com.skfo763.repository.data.UserData
import com.skfo763.storage.xls.XlsStorageManager
import io.reactivex.Single
import java.lang.IllegalStateException

class UserRepository(
    private val xlsStorageManager: XlsStorageManager? = null,
    private val api: UserRxApi = RetroBuilder().getRetrofit().create(UserRxApi::class.java)
) {

    private val userConverter = UserConverter()
    private val policyConverter = PolicyConverter()

    fun registerUser(userData: UserData) = api.registerUser(
        userConverter.convertUserDataToModel(userData)
    )

    fun getUserInfo(userIdx: Int) = api.getDefaultUserInfo(userIdx).map {
        userConverter.convertUserModelToData(it)
    }

    fun getUserPolicy(userIdx: Int) = api.getUserPolicy(userIdx).map {
        policyConverter.convertModelToPolicyData(it)
    }

    fun saveXls(data: UserData) = Single.create<Uri?>{
        xlsStorageManager?.apply{
            if(!checkFileExists("마이 데이터 - 내 정보")) {
                setSheet("내 정보")
                setMyInfoRow(listOf(data.getMyInfoXlsData()), "내 정보")
                saveExcel("마이 데이터 - 내 정보")
            }
            getUrl()?.let { url ->
                it.onSuccess(url)
            } ?: kotlin.run {
                it.onError(IllegalStateException())
            }
        } ?: kotlin.run {
            Log.e(this::class.java.name, "xlsStorageManager is null")
        }
    }


}