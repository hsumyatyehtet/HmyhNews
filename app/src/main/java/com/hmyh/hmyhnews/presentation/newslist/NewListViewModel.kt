package com.hmyh.hmyhnews.presentation.newslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hmyh.domain.ArticleListVO
import com.hmyh.hmyhnews.domain.NewsListVO
import com.hmyh.hmyhnews.framework.model.HmyhNewsModel
import com.hmyh.hmyhnews.framework.model.impl.HmyhNewsModelImpl
import com.hmyh.hmyhnews.framework.util.BASE_URL

class NewListViewModel : ViewModel() {

    private val mModel: HmyhNewsModel = HmyhNewsModelImpl

    private val mNew: LiveData<NewsListVO> = mModel.getNewVO()

    private var mTotalResult: Long? = null
    private var mPageSize: Int = 50

    private var mTotalPage: Long = 0
    private var mPage: Int = 1

    fun onUiReady() {
        mModel.loadNewsList(
            mPage,
            mPageSize,
            onSuccess = {
                it.totalResults?.let { totalResult ->
                    mTotalResult = totalResult

                    mTotalResult?.let { result ->
                        mTotalPage = result / mPageSize.toLong()
                    }

                }
            },
            onFailure = {

            }
        )
    }

    fun loadMoreNewsList() {
        if (mPage.toLong() < mTotalPage) {
            mPage++
            mModel.loadMoreNewList(BASE_URL,
                mPage, mPageSize, onSuccess = {newList->

                },
                onFailure = {

                })
        }
    }

    fun getNew(): LiveData<NewsListVO> {
        return mNew
    }

}