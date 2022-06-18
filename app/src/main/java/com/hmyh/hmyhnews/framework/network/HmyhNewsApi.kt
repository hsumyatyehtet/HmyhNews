package com.hmyh.hmyhnews.framework.network

import com.hmyh.hmyhnews.domain.NewsListVO
import com.hmyh.hmyhnews.framework.util.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface HmyhNewsApi {

    @GET(GET_NEW_LIST)
    fun loadNewsList(
        @Query(PARAM_QUERY) q: String,
        @Query(PARAM_API_KEY) apiKey: String,
        @Query(PARAM_PAGE_SIZE)pageSize: Int,
        @Query(PARAM_PAGE)page: Int
    ): Observable<NewsListVO>

    @GET
    fun loadMoreNewList(
        @Url url: String,
        @Query(PARAM_QUERY) q: String,
        @Query(PARAM_API_KEY) apiKey: String,
        @Query(PARAM_PAGE_SIZE)pageSize: Int,
        @Query(PARAM_PAGE)page: Int
    ): Observable<NewsListVO>

//    @GET
//    fun loadMoreNewList(
//        @Url url: String,
//        @Query(PARAM_QUERY) q: String,
//        @Query(PARAM_API_KEY) apiKey: String,
//        @Query(PARAM_PAGE_SIZE)pageSize: Int,
//        @Query(PARAM_PAGE)page: Int
//    ): Observable<NewsApiErrorResponse<NewsListVO>>

}