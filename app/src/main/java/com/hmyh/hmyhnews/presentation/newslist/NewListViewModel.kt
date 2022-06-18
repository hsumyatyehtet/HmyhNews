package com.hmyh.hmyhnews.presentation.newslist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hmyh.domain.ArticleListVO
import com.hmyh.domain.NewsListVO
import com.hmyh.hmyhnews.framework.model.HmyhNewsModel
import com.hmyh.hmyhnews.framework.model.impl.HmyhNewsModelImpl

class NewListViewModel: ViewModel() {

    private val mModel: HmyhNewsModel = HmyhNewsModelImpl

    private var mArticleList: MutableList<ArticleListVO> = mutableListOf()
    private var mNewsLiveData = MutableLiveData<MutableList<ArticleListVO>>()

    fun onUiReady() {
        mModel.loadNewsList(
            onSuccess = {
                Log.d("newList",it.toString())
                it.articleList.let {articleList->
                    mArticleList.addAll(articleList)
                    mNewsLiveData.postValue(mArticleList)
                }
            },
            onFailure = {

            }
        )
    }

    fun getArticleList(): MutableLiveData<MutableList<ArticleListVO>>{
        return mNewsLiveData
    }


}