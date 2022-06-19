package com.hmyh.hmyhnews.framework.model.impl

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.hmyh.hmyhnews.domain.NewsListVO
import com.hmyh.hmyhnews.framework.model.BaseAppModel
import com.hmyh.hmyhnews.framework.model.HmyhNewsModel
import com.hmyh.hmyhnews.framework.network.response.ErrorResponse
import com.hmyh.hmyhnews.framework.util.API_KEY_DATA
import com.hmyh.hmyhnews.framework.util.GET_NEW_LIST
import com.hmyh.hmyhnews.framework.util.QUARY_DATA
import com.hmyh.hmyhnews.framework.util.subscribeDBWithCompletable
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object HmyhNewsModelImpl : BaseAppModel(), HmyhNewsModel {

    @SuppressLint("CheckResult")
    override fun loadNewsList(
        page: Int,
        mPageSize: Int,
        onSuccess: (newsListVO: NewsListVO) -> Unit,
        onFailure: (String) -> Unit
    ) {

        mApi.loadNewsList(QUARY_DATA, API_KEY_DATA, mPageSize, page).subscribeOn(
            Schedulers.io()
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { newListVO ->
                    onSuccess(newListVO)
                    mDatabase.newListDao().insertNewData(newListVO).subscribeDBWithCompletable()
                }
            }, {
                it.message?.let { errorMessage ->
                    onFailure(errorMessage)
                }

            })

    }

    override fun getNewVO(): LiveData<List<NewsListVO>> {
        return mDatabase.newListDao().getNew()
    }

    @SuppressLint("CheckResult")
    override fun loadMoreNewList(
        url: String,
        page: Int,
        pageSie: Int,
        onSuccess: (newsListVO: NewsListVO) -> Unit,
        onFailure: (String) -> Unit
    ) {

        mApi.loadMoreNewList(url + GET_NEW_LIST, QUARY_DATA, API_KEY_DATA, pageSie, page)
            .subscribeOn(
                Schedulers.io()
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    it?.let { newListVo ->

                        if (newListVo.status == "ok") {
                            onSuccess(newListVo)
                            mDatabase.newListDao().insertNewData(newListVo)
                                .subscribeDBWithCompletable()
                        } else {
                            onFailure("error")
                        }

                    }
                },
                {
                    it.message?.let { it1 ->
                        onFailure(it1)
                    }
                }
            )
    }

    @SuppressLint("CheckResult")
    override fun loadSearchNewsList(
        page: Int,
        pageSie: Int,
        query: String,
        onSuccess: (newsListVO: NewsListVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mApi.loadNewsList(query, API_KEY_DATA, pageSie, page).subscribeOn(
            Schedulers.io()
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { newListVO ->
                    onSuccess(newListVO)
                }
            }, {
                it.message?.let { errorMessage ->
                    onFailure(errorMessage)
                }

            })
    }

    @SuppressLint("CheckResult")
    override fun loadMoreSearchNewsList(
        url: String,
        page: Int,
        pageSie: Int,
        query: String,
        onSuccess: (newsListVO: NewsListVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mApi.loadMoreNewList(url + GET_NEW_LIST, query, API_KEY_DATA, pageSie, page)
            .subscribeOn(
                Schedulers.io()
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    it?.let { newListVo ->

                        if (newListVo.status == "ok") {
                            onSuccess(newListVo)

                        } else {
                            onFailure("error")
                        }

                    }
                },
                {
                    it.message?.let { it1 ->
                        onFailure(it1)
                    }
                }
            )
    }

}