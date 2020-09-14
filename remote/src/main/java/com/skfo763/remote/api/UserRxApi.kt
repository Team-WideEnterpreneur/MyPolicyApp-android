package com.skfo763.remote.api

import com.skfo763.remote.data.PolicyModel
import com.skfo763.remote.data.UserModel
import io.reactivex.Single
import retrofit2.http.*

interface UserRxApi {

    companion object {
        private const val BASE_ENDPOINT = "/user"
    }

    @POST(BASE_ENDPOINT)
    fun registerUser(
        @Body userModel: UserModel
    ): Single<Any>


    @GET("$BASE_ENDPOINT/{userIdx}")
    fun getDefaultUserInfo(
        @Path("userIdx") userIdx: Int
    ): Single<UserModel>

    @GET("$BASE_ENDPOINT/{userIdx}/policy")
    fun getUserPolicy(
        @Path("userIdx") userIdx: Int
    ): Single<PolicyModel>


}