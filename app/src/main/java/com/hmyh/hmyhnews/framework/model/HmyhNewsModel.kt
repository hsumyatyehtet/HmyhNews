package com.hmyh.hmyhnews.framework.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hmyh.domain.NewsListVO

interface HmyhNewsModel {

    fun loadNewsList(
        onSuccess: (newsListVO: NewsListVO)->Unit,
        onFailure: (String)->Unit
    )

}