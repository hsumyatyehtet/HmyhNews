package com.hmyh.news.presentation.newslist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hmyh.domain.ArticleListVO
import com.hmyh.hmyhnews.databinding.ViewHolderInNewListBinding
import com.hmyh.hmyhnews.presentation.BaseRecyclerAdapter

class NewsListAdapter(val delegate: Delegate):
BaseRecyclerAdapter<NewsListViewHolder, ArticleListVO>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        return NewsListViewHolder(
            ViewHolderInNewListBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            ),
            delegate
        )
    }

    interface Delegate{
        fun onTapNewsItem(author: String)
    }

}