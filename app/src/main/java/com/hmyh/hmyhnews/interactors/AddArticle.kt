package com.hmyh.hmyhnews.interactors

import com.hmyh.domain.ArticleListVO
import com.hmyh.hmyhnews.data.ArticleRepository

class AddArticle(private val articleDataRepository: ArticleRepository) {

    suspend operator fun invoke(articleList: List<ArticleListVO>) =
        articleDataRepository.addArticle(articleList)

}