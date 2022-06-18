package com.hmyh.hmyhnews.presentation.newslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hmyh.domain.ArticleListVO
import com.hmyh.hmyhnews.domain.NewsListVO
import com.hmyh.hmyhnews.framework.model.HmyhNewsModel
import com.hmyh.hmyhnews.framework.model.impl.HmyhNewsModelImpl

class NewListViewModel : ViewModel() {

    private val mModel: HmyhNewsModel = HmyhNewsModelImpl

    private val mNew: LiveData<NewsListVO> = mModel.getNewVO()

    private var mTotalResult: Long? = null
    private var mPageSize: Int=50

    fun onUiReady() {
        mModel.loadNewsList(
            mPageSize,
            onSuccess = {
                it.totalResults?.let { totalResult ->
                    mTotalResult = totalResult
                }
            },
            onFailure = {

            }
        )
    }

    fun loadMoreNewsList(){

    }

    fun getNew(): LiveData<NewsListVO> {
        return mNew
    }

}