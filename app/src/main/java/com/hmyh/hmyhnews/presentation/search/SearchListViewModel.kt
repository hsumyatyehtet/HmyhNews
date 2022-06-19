package com.hmyh.hmyhnews.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hmyh.domain.ArticleListVO
import com.hmyh.hmyhnews.domain.NewsListVO
import com.hmyh.hmyhnews.framework.model.HmyhNewsModel
import com.hmyh.hmyhnews.framework.model.impl.HmyhNewsModelImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchListViewModel: ViewModel() {

    private val mModel: HmyhNewsModel = HmyhNewsModelImpl

    private val mArticleList = ArrayList<ArticleListVO>()
    private val mArticleListLiveData = MutableLiveData<List<ArticleListVO>>()

    private var mErrorMessage: MutableLiveData<String> = MutableLiveData<String>()

    private var mTotalResult: Long? = null
    private var mPageSize: Int = 50

    private var mTotalPage: Long = 0
    private var mPage: Int = 1

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

    fun getSearchNewsList(): MutableLiveData<List<ArticleListVO>>{
        return mArticleListLiveData
    }

}