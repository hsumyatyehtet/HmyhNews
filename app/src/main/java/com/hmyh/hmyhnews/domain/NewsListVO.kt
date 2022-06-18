package com.hmyh.hmyhnews.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.hmyh.domain.ArticleListVO

@Entity(tableName = "new_list")
data class NewsListVO(

    @PrimaryKey(autoGenerate = true)
    var newsId: Int = 0,

    @SerializedName("status")
    var status: String? = null,

    @SerializedName("totalResults")
    var totalResults: Long? = null,

    @SerializedName("articles")
    var articleList: MutableList<ArticleListVO>? = null

)