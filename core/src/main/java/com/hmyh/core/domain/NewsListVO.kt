package com.hmyh.domain

data class NewsListVO(

    var status: String?=null,

    var totalResults: Long?=null,

    var articleList: MutableList<ArticleListVO>

)