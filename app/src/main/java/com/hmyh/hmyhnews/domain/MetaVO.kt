package com.hmyh.hmyhnews.domain

import com.google.gson.annotations.SerializedName

class MetaVO {

    @SerializedName("total")
    val total: Int = 0

    @SerializedName("count")
    val count: Int = 0

    @SerializedName("per_page")
    val perPage: Int = 0

    @SerializedName("current_page")
    val currentPage: Int = 0

    @SerializedName("total_pages")
    val totalPages: Int = 0

    @SerializedName("next_pages")
    val nextPage: String? = null

    @SerializedName("previous_pages")
    val previousPage: String? = null

}