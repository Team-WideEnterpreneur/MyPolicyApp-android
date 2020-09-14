package com.skfo763.remote.base

import com.google.gson.annotations.SerializedName

class BaseModel<T> (
    @SerializedName("code") val code: Int?,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: T
)