package com.hmyh.hmyhnews.framework.model

import android.content.Context
import com.google.gson.GsonBuilder
import com.hmyh.hmyhnews.BuildConfig
import com.hmyh.hmyhnews.framework.network.HmyhNewsApi
import com.hmyh.hmyhnews.framework.util.ApiConstants
import com.hmyh.hmyhnews.framework.util.BASE_URL
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseAppModel: BaseModel() {

    protected lateinit var mApi: HmyhNewsApi

    override fun init(context: Context) {
        initNetwork(context)
      //  initDatabase(context)
    }

    private fun initDatabase(context: Context) {
        //mDatabase = HmyhAssignmentThreeDatabase.getDatabase(context)
    }

    private fun initNetwork(context: Context) {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .apply {
                if (BuildConfig.DEBUG) addInterceptor(ChuckInterceptor(context))
            }
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .addHeader(ApiConstants.HEADER_ACCEPT, ApiConstants.HEADER_VALUE)
                        .addHeader(ApiConstants.HEADER_CONTENT_TYPE, ApiConstants.HEADER_VALUE)
                        .build()
                )
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        mApi = retrofit.create(HmyhNewsApi::class.java)
    }

}