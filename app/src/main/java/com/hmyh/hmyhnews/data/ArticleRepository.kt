package com.hmyh.hmyhnews.data

import com.hmyh.domain.ArticleListVO

class ArticleRepository(private val dataSource: ArticleDataSource) {

    suspend fun addArticle(articleList: List<ArticleListVO>) =
        dataSource.addArticleList(articleList)

    suspend fun getArticleList(articleList: List<ArticleListVO>) =
        dataSource.getArticleList()

}