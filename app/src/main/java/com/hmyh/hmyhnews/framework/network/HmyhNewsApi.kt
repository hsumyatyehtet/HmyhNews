package com.hmyh.hmyhnews.framework.network

import com.hmyh.hmyhnews.domain.NewsListVO
import com.hmyh.hmyhnews.framework.util.GET_NEW_LIST
import com.hmyh.hmyhnews.framework.util.PARAM_API_KEY
import com.hmyh.hmyhnews.framework.util.PARAM_PAGE_SIZE
import com.hmyh.hmyhnews.framework.util.PARAM_QUERY
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface HmyhNewsApi {

    @GET(GET_NEW_LIST)
    fun loadNewsList(
        @Query(PARAM_QUERY) q: String,
        @Query(PARAM_API_KEY) apiKey: String,
        @Query(PARAM_PAGE_SIZE)pageSize: Int
    ): Observable<NewsListVO>

}