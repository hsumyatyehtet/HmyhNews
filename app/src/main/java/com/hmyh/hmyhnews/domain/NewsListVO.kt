package com.hmyh.domain

import com.google.gson.annotations.SerializedName

data class NewsListVO(

    @SerializedName("status")
    var status: String?=null,

    @SerializedName("totalResults")
    var totalResults: Long?=null,

    @SerializedName("articles")
    var articleList: MutableList<ArticleListVO>

)