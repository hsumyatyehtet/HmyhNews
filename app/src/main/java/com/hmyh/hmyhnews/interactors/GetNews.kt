package com.hmyh.hmyhnews.interactors

import com.hmyh.hmyhnews.data.NewsRepository
import com.hmyh.hmyhnews.domain.NewsListVO

class GetNews(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(newsListVO: NewsListVO) =
        newsRepository.getNews()
}