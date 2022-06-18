package com.hmyh.hmyhnews.data

import com.hmyh.domain.ArticleListVO

interface ArticleDataSource {

    suspend fun addArticleList(article: List<ArticleListVO>)

    suspend fun getArticleList(): List<ArticleListVO>

}