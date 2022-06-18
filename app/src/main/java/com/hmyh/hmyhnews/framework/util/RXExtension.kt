package com.hmyh.hmyhnews.framework.util

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.hmyh.hmyhnews.framework.domain.MetaVO
import com.hmyh.hmyhnews.framework.network.response.BaseResponse
import com.hmyh.hmyhnews.framework.network.response.DataResponse
import com.hmyh.hmyhnews.framework.network.response.ErrorResponse
import com.hmyh.hmyhnews.framework.network.response.MoreDataResponse
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
fun <T : MoreDataResponse<W>, W> Observable<T>.subscribeMoreDataResponse(
    success: (W, MetaVO?) -> Unit,
    failure: (String) -> Unit,
    subscribeOnScheduler: Scheduler = Schedulers.io()
) {
    this.subscribeOn(subscribeOnScheduler)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            it.processResponse({ data, meta ->
                success(data, meta)
            }, { message ->
                failure(message)
            })
        }, {
            it.handleError(failure)
        })
}

//Data Response
@SuppressLint("CheckResult")
fun <T : DataResponse<W>, W> Observable<T>.subscribeDataResponse(
    success: (W) -> Unit,
    failure: (String) -> Unit
) {
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            it.processDataResponse({ data ->
                success(data)
            }, { message ->
                failure(message)
            })
        }, {

            it.handleError(failure)
            Log.e("LOgin Error",failure.toString())

        })
}

@SuppressLint("CheckResult")
fun Completable.subscribeDBWithCompletable() {
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe ({
            Log.d("Database CRUD", "Operation is successful.")
        },{
            Log.d("Database CRUD", "Operation is a failure")
        })
}

//Base Response
@SuppressLint("CheckResult")
fun Observable<BaseResponse>.subscribeBaseResponse(
    success: (String) -> Unit,
    failure: (String) -> Unit
) {
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            it.processBaseResponse({
                success.invoke(it)
            }, {
                failure.invoke(it)
            })
        }, {
            it.handleError(failure)
        })
}

private fun <T> MoreDataResponse<T>?.processResponse(
    success: (T, MetaVO?) -> Unit,
    failure: (String) -> Unit
) {
    if (this != null) {
        if (this.isResponseOk()) {
            success.invoke(this.data!!, this.meta)
        } else {
            failure(this.message)
        }
    } else {
        failure.invoke("Response Was Null")
    }
}

private fun <T> DataResponse<T>?.processDataResponse(
    success: (T) -> Unit,
    failure: (String) -> Unit
) {
    if (this != null) {
        if (this.isResponseOk() && this.data != null) {
            success.invoke(this.data)
        } else {
            failure(this.message)
            Log.e("messageError",this.message)
        }
    } else {
        failure.invoke("Response Was Null")
    }
}

private fun BaseResponse.processBaseResponse(success: (String) -> Unit, failure: (String) -> Unit) {
    if (this.isResponseOk()) {
        success.invoke(this.message)
    } else {
        failure(this.message)
    }
}

fun Throwable.handleError(failure: (String) -> Unit) {

    if (this is HttpException) {
        val errorBody = this.response()?.errorBody()?.string()
        val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)

        if (this.code() == 500) {
            failure("System Error!")
        } else {
            failure(errorResponse.message)
        }

    } else if (this is java.io.IOException){
        this.message
        this.message?.let { Log.d("gallery error", it) }
        failure("No Internet Connection!")
    } else {
        failure("Connection Error!")
    }

}


inline fun <T: Any> liveData(liveBlock: MutableLiveData<T>.() -> Unit): LiveData<T> {
    return MutableLiveData<T>().apply{
        liveBlock()
    }
}
fun Disposable.disposeBy(bag: CompositeDisposable) = bag.add(this)

fun onWait(millisec: Long, doOnNext: () -> Unit): Disposable {
   return Observable.timer(millisec, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {

            doOnNext()

        }
}