package com.hmyh.hmyhnews.data

import com.hmyh.hmyhnews.domain.NewsListVO

class NewsRepository(private val dataSource: NewsListDataSource) {

    suspend fun addNews(newsListVO: NewsListVO) =
        dataSource.addNews(newsListVO)

    suspend fun getNews() =
        dataSource.getNews()

}