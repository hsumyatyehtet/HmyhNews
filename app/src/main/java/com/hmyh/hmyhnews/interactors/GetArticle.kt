package com.hmyh.hmyhnews.interactors

import com.hmyh.domain.ArticleListVO
import com.hmyh.hmyhnews.data.ArticleRepository

class GetArticle(private val articleDataRepository: ArticleRepository) {
    suspend operator fun invoke(articleList: List<ArticleListVO>) =
        articleDataRepository.getArticleList(articleList)
}