package com.hmyh.hmyhnews.presentation.newslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hmyh.domain.ArticleListVO
import com.hmyh.hmyhnews.domain.NewsListVO
import com.hmyh.hmyhnews.framework.model.HmyhNewsModel
import com.hmyh.hmyhnews.framework.model.impl.HmyhNewsModelImpl

class NewListViewModel: ViewModel() {

    private val mModel: HmyhNewsModel = HmyhNewsModelImpl

    private val mNew: LiveData<NewsListVO> = mModel.getNewVO()

    private var mArticleList: MutableList<ArticleListVO> = mutableListOf()
    private var mNewsLiveData = MutableLiveData<MutableList<ArticleListVO>>()

    fun onUiReady() {
        mModel.loadNewsList(
            onSuccess = {

            },
            onFailure = {

            }
        )
    }

    fun getNew(): LiveData<NewsListVO>{
        return mNew
    }

    fun getArticleList(): MutableLiveData<MutableList<ArticleListVO>>{
        return mNewsLiveData
    }


}