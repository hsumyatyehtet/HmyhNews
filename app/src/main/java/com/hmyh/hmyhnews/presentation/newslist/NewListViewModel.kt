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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewListViewModel : ViewModel(),NewsListAdapter.Delegate {

    private val mModel: HmyhNewsModel = HmyhNewsModelImpl

    private var mNew: LiveData<List<NewsListVO>> = mModel.getNewVO()

    private var mErrorMessage: MutableLiveData<String> = MutableLiveData<String>()
    private var mErrorMessageMore: MutableLiveData<String> = MutableLiveData<String>()

    private val navigateArticleLiveData: MutableLiveData<ArticleListVO> = MutableLiveData()

    private var mTotalResult: Long? = null
    private var mPageSize: Int = 50

    private var mTotalPage: Long = 0
    private var mPage: Int = 1

    private var progressLiveData: MutableLiveData<Int> = MutableLiveData<Int>()

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
                GlobalScope.launch {
                    mErrorMessage.postValue(it)
                }

            }
        )
    }

    fun loadMoreNewsList() {
        if (mPage.toLong() < mTotalPage) {
            mPage++
            GlobalScope.launch {
                progressLiveData.postValue(1)
            }
            mModel.loadMoreNewList(BASE_URL,
                mPage, mPageSize,
                onSuccess = { newList ->
                    GlobalScope.launch {
                        progressLiveData.postValue(0)
                    }
                },
                onFailure = {
                    GlobalScope.launch {
                        progressLiveData.postValue(0)
                        mErrorMessageMore.postValue(it)
                    }

                })
        }
    }

    fun getNew(): LiveData<List<NewsListVO>> {
        return mNew
    }

    fun getErrorMessage(): MutableLiveData<String>{
       return mErrorMessage
    }

    fun getErrorMessageMore(): MutableLiveData<String>{
        return mErrorMessageMore
    }

    fun getShowOrHideProgress(): MutableLiveData<Int> {
        return progressLiveData
    }

    override fun onTapNewsItem(article: ArticleListVO) {
        GlobalScope.launch {
            navigateArticleLiveData.postValue(article)
        }
    }

    fun getNavigateSearchListToDetailData(): LiveData<ArticleListVO>{
        return navigateArticleLiveData
    }

}