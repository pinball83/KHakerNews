package com.thrd.hackernewskotlin.data.remote

import com.thrd.hackernewskotlin.domain.model.News
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.Observable

/**
 * @author thrd
 * @version 13.12.16.
 */
class RestApiAdapter {
    private val hackerNewsApi: HackerNewsApi

    init {
        val retrofitHackerNews = Retrofit.Builder()
                .baseUrl("https://hacker-news.firebaseio.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        hackerNewsApi = retrofitHackerNews.create(HackerNewsApi::class.java)
    }

    fun getLastNews(limit: Int): Observable<List<Int>> = hackerNewsApi.getLast(limit)

    fun  getItem(id: Int): Observable<News> = hackerNewsApi.getItem(id)
}