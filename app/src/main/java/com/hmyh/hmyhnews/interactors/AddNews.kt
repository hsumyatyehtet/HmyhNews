package com.hmyh.hmyhnews.interactors

import com.hmyh.hmyhnews.data.NewsRepository
import com.hmyh.hmyhnews.domain.NewsListVO

class AddNews(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(newsListVO: NewsListVO) =
        newsRepository.addNews(newsListVO)
}