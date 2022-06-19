package com.hmyh.hmyhnews.data

import com.hmyh.hmyhnews.domain.NewsListVO

interface NewsListDataSource {

    suspend fun addNews(newsListVO: NewsListVO)

    suspend fun getNews(): List<NewsListVO>

}