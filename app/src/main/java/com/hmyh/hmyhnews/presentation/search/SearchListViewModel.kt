package com.hmyh.hmyhnews.presentation.search

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

class SearchListViewModel: ViewModel(),NewsSearchAdapter.Delegate {

    private val mModel: HmyhNewsModel = HmyhNewsModelImpl

    private val mArticleList = ArrayList<ArticleListVO>()
    private val mArticleListLiveData = MutableLiveData<List<ArticleListVO>>()

    private val navigateArticleLiveData: MutableLiveData<ArticleListVO> = MutableLiveData()

    private var mErrorMessage: MutableLiveData<String> = MutableLiveData<String>()
    private var mErrorMessageMore: MutableLiveData<String> = MutableLiveData<String>()

    private var mTotalResult: Long? = null
    private var mPageSize: Int = 50

    private var mTotalPage: Long = 0
    private var mPage: Int = 1

    private var progressLiveData: MutableLiveData<Int> = MutableLiveData<Int>()

    fun loadSearchNews(searchWord: String){
        mModel.loadSearchNewsList(
            mPage,
            mPageSize,
            searchWord,
            onSuccess = {
                it.totalResults?.let { totalResult ->
                    mTotalResult = totalResult

                    mTotalResult?.let { result ->
                        mTotalPage = result / mPageSize.toLong()
                    }
                }

                it.articleList?.let { articleList->
                    mArticleList.addAll(articleList)
                    mArticleListLiveData.value = mArticleList
                }
            },
            onFailure = {
                GlobalScope.launch {
                    mErrorMessage.postValue(it)
                }

            }
        )
    }

    fun loadMoreSearchNews(searchWord: String){
        if (mPage.toLong() < mTotalPage) {
            mPage++
            GlobalScope.launch {
                progressLiveData.postValue(1)
            }
            mModel.loadMoreNewList(
                BASE_URL,
                mPage, mPageSize,
                onSuccess = { newList ->

                    newList.articleList?.let { articleList->
                        mArticleList.addAll(articleList)
                        mArticleListLiveData.value = mArticleList
                    }

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

    fun getSearchNewsList(): MutableLiveData<List<ArticleListVO>>{
        return mArticleListLiveData
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

    override fun onTapNewsSearchItem(article: ArticleListVO) {
        GlobalScope.launch {
            navigateArticleLiveData.postValue(article)
        }
    }

    fun getNavigateSearchListToDetailData(): LiveData<ArticleListVO>{
        return navigateArticleLiveData
    }

}