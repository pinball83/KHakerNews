package com.thrd.hackernewskotlin.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.thrd.hackernewskotlin.R
import com.thrd.hackernewskotlin.data.remote.RestApiAdapter
import com.thrd.hackernewskotlin.domain.model.News
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    @BindView(R.id.items) lateinit var items: RecyclerView
    private val api = RestApiAdapter()

    private var s = observable()

    private fun observable(): Observable<MutableList<News>> {
        return api.getLastNews(20)
                .subscribeOn(Schedulers.io())
                .flatMapIterable({ it -> it })
                .flatMap { it -> api.getItem(it) }
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        items.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        s.subscribe({ next ->
            items.adapter = NewsAdapter(next)
            items.adapter.notifyDataSetChanged()
        }, { it: Throwable? -> error("Error!") })
    }

    override fun onPause() {
        super.onPause()
        s.unsubscribeOn(AndroidSchedulers.mainThread())
    }
}


