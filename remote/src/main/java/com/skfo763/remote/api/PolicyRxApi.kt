package com.skfo763.remote.api

import com.skfo763.remote.data.PolicyModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PolicyRxApi {

    companion object {
        private const val BASE_ENDPOINT = "/policy"
    }

    @GET("$BASE_ENDPOINT/{idx}")
    fun getPolicyData(
        @Path("idx") policyIdx: Int
    ): Single<PolicyModel>

    @GET("$BASE_ENDPOINT/all")
    fun getPolicySummaryList(): Single<List<PolicyModel>>

}
