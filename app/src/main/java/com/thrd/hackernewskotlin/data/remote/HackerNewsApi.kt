package com.thrd.hackernewskotlin.data.remote

import com.thrd.hackernewskotlin.domain.model.News
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * @author thrd
 * @version 13.12.16.
 */
interface HackerNewsApi {

    @GET("/v0/newstories.json?orderBy=\"\$key\"")
    fun getLast(@Query("limitToFirst") limit: Int): Observable<List<Int>>

    @GET("/v0/item/{id}.json")
    fun getItem(@Path("id") id: Int): Observable<News>
}