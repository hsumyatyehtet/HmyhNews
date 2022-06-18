package com.hmyh.hmyhnews.framework.model

import com.hmyh.hmyhnews.domain.NewsListVO

interface HmyhNewsModel {

    fun loadNewsList(
        onSuccess: (newsListVO: NewsListVO)->Unit,
        onFailure: (String)->Unit
    )

}