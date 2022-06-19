package com.hmyh.hmyhnews.presentation.search

import com.bumptech.glide.Glide
import com.hmyh.domain.ArticleListVO
import com.hmyh.hmyhnews.R
import com.hmyh.hmyhnews.databinding.ViewHolderInSearchBinding
import com.hmyh.hmyhnews.presentation.BaseViewHolder
import com.hmyh.hmyhnews.presentation.newslist.NewsListAdapter

class NewsSearchViewHolder(
    private val binding: ViewHolderInSearchBinding,
    private val delegate: NewsSearchAdapter.Delegate
) : BaseViewHolder<ArticleListVO>(binding.root) {

    init {
        itemView.setOnClickListener {
            mData?.let { articleListVO ->
                delegate.onTapNewsSearchItem(articleListVO)
            }
        }
    }

    override fun bindData(data: ArticleListVO) {
        mData = data

        Glide.with(itemView.context)
            .load(data.urlToImage)
            .circleCrop()
            .into(binding.ivNewsSearchItem)

        binding.tvNewsSearchTitle.text = data.title ?: ""
    }
}