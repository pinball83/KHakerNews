package com.thrd.hackernewskotlin.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thrd.hackernewskotlin.R
import com.thrd.hackernewskotlin.domain.model.News
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(val newses: List<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return newses.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newses[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news: News) {
            with(news) {
                itemView.tvTitle.text = news.title
                itemView.tvUrl.text = news.url
                itemView.tvUser.text = news.by
            }
        }
    }
}