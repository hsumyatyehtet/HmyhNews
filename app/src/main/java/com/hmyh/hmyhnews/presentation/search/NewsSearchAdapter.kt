package com.hmyh.hmyhnews.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hmyh.domain.ArticleListVO
import com.hmyh.hmyhnews.databinding.ViewHolderInSearchBinding
import com.hmyh.hmyhnews.presentation.BaseRecyclerAdapter

class NewsSearchAdapter():
BaseRecyclerAdapter<NewsSearchViewHolder, ArticleListVO>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSearchViewHolder {
        return NewsSearchViewHolder(
            ViewHolderInSearchBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    interface Delegate{
        fun onTapNewsItem(author: com.hmyh.domain.ArticleListVO)
    }

}