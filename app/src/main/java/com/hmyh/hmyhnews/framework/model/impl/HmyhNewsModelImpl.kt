package com.hmyh.hmyhnews.framework.model.impl

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hmyh.domain.NewsListVO
import com.hmyh.hmyhnews.framework.model.BaseAppModel
import com.hmyh.hmyhnews.framework.model.HmyhNewsModel
import com.hmyh.hmyhnews.framework.util.API_KEY_DATA
import com.hmyh.hmyhnews.framework.util.QUARY_DATA
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object HmyhNewsModelImpl : BaseAppModel(), HmyhNewsModel {

    @SuppressLint("CheckResult")
    override fun loadNewsList(
        onSuccess: (newsListVO: NewsListVO) -> Unit,
        onFailure: (String) -> Unit
    ) {

        mApi.loadNewsList(QUARY_DATA, API_KEY_DATA).subscribeOn(
            Schedulers.io()
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { newListVO ->
                    onSuccess(newListVO)
                }
            }, {

            })

    }

}