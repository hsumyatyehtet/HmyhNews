package com.hmyh.domain

data class ArticleListVO(

    var source: SourceVO?=null,

    var author: String?=null,

    var title: String?=null,

    var description: String?=null,

    var url: String?=null,

    var urlToImage: String?=null,

    var publishAt: String?=null,

    var content: String?=null

)