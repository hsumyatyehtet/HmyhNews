package com.hmyh.hmyhnews.framework.network.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Hsu Myat Ye Htet on 17/June/2022
 */
data class DataResponse<T>(
    @SerializedName("code")
    val code: Int = 0,

    @SerializedName("message")
    val message: String = "",

    @SerializedName("data")
    val data: T? = null
) {
    fun isResponseOk() = code in 200..299 && data != null
}