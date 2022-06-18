package com.hmyh.hmyhnews.framework.network.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Hsu Myat Ye Htet on 17/June/2022
 */
data class ErrorResponse(

    @SerializedName("code")
    val code: Int? = 0,
    @SerializedName("message")
    val message: String
) {

}