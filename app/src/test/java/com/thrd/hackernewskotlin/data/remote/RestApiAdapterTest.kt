package com.thrd.hackernewskotlin.data.remote

import com.thrd.hackernewskotlin.domain.model.News
import org.junit.Before
import org.junit.Test
import rx.schedulers.Schedulers
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * @author thrd
 * *
 * @version 13.12.16.
 */
class RestApiAdapterTest {

    lateinit var api: RestApiAdapter

    @Before
    fun setUp() {
        api = RestApiAdapter()
    }

    @Test
    fun getLastNews() {
        val call = api.getLastNews(20)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.immediate())
                .toBlocking()
                .first()
        assertEquals(20, call.size)
    }

    @Test
    fun getItem() {
        val call = api.getItem(13215292)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.immediate())
                .toBlocking()
                .first()

        assertNotNull(call)
        assertTrue(call is News)
    }

    @Test
    fun collectItems() {
        val items = api.getLastNews(20)
                .subscribeOn(Schedulers.io())
                .flatMapIterable({ it -> it })
                .flatMap { it -> api.getItem(it) }
                .toList()
                .observeOn(Schedulers.immediate())
                .toBlocking()
                .first()
        assertNotNull(items)
        assertTrue(items is List<News>)
    }
}