package com.hmyh.hmyhnews.framework.network.response

import com.google.gson.annotations.SerializedName
import com.hmyh.hmyhnews.domain.MetaVO


/**
 * Created by Hsu Myat Ye Htet on 17/June/2022
 */
data class MoreDataResponse<T>(
    @SerializedName("code")
    val code: Int = 0,

    @SerializedName("message")
    val message: String = "",

    @SerializedName("data")
    val data: T? = null,

    @SerializedName("meta")
    val meta: MetaVO? = null
) {
    fun isResponseOk() = code in 200..299 && data!= null
}