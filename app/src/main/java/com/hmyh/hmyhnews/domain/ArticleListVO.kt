package com.hmyh.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ArticleListVO(

    @SerializedName("source")
    var source: SourceVO?=null,

    @SerializedName("author")
    var author: String?=null,

    @SerializedName("title")
    var title: String?=null,

    @SerializedName("description")
    var description: String?=null,

    @SerializedName("url")
    var url: String?=null,

    @SerializedName("urlToImage")
    var urlToImage: String?=null,

    @SerializedName("publishedAt")
    var publishAt: String?=null,

    @SerializedName("content")
    var content: String?=null

):Parcelable