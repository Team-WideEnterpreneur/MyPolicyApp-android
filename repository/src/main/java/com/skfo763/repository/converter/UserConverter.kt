package com.skfo763.repository.converter

import com.skfo763.remote.data.UserModel
import com.skfo763.repository.data.UserData

class UserConverter {
    
    fun convertUserModelToData(userModel: UserModel) = UserData(
        userModel.userIdx,
        userModel.address,
        userModel.age,
        userModel.schoolCredit,
        userModel.gender,
        userModel.grade,
        userModel.insuraceFare,
        userModel.income,
        userModel.name,
        userModel.familyStat,
        userModel.incomeStat,
        userModel.employmentState
    )

    fun convertUserDataToModel(userData: UserData) = UserModel(
        userData.userIdx,
        userData.address,
        userData.age,
        userData.schoolCredit,
        userData.gender,
        userData.grade,
        userData.insuraceFare,
        userData.income,
        userData.name,
        userData.familyStat,
        userData.incomeStat,
        userData.employmentState
    )
    
}