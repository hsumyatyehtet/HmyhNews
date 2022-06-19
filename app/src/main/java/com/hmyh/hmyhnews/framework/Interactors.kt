package com.hmyh.hmyhnews.framework

import com.hmyh.hmyhnews.interactors.AddArticle
import com.hmyh.hmyhnews.interactors.AddNews
import com.hmyh.hmyhnews.interactors.GetArticle
import com.hmyh.hmyhnews.interactors.GetNews

data class Interactors(
    val addArticle: AddArticle,
    val getArticle: GetArticle,
    val addNews: AddNews,
    val getNews: GetNews
)