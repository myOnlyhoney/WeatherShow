package com.example.weathershow.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("api_res_code")
    val code: Int,
    @SerializedName("api_res_error")
    val msg: String? = null,
    @SerializedName("api_res_body")
    val data: T
)
