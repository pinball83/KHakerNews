package com.thrd.hackernewskotlin.view

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.thrd.hackernewskotlin.BuildConfig
import com.thrd.hackernewskotlin.R
import com.thrd.hackernewskotlin.domain.model.News
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertNotNull

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(23))
class MainActivityTest {
    @Test
    fun testMainActivity() {
        val mainActivity = Robolectric.setupActivity(MainActivity::class.java)
        assertNotNull(mainActivity)
        val recycler = mainActivity.findViewById(R.id.items) as RecyclerView
        assertNotNull(recycler)
        recycler.adapter = NewsAdapter(listOf(News(1, "title", "url", "man", "type", 1)))
        recycler.layoutManager = LinearLayoutManager(mainActivity)
        recycler.findViewHolderForAdapterPosition(0).itemView.performClick()
//        verify(fragment.presenter).onEntryClicked(MyNavigationEntry.XYZ)
    }
}