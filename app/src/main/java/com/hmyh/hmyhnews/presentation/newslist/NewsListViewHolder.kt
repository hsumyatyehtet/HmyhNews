package com.hmyh.hmyhnews.presentation.newslist

import com.bumptech.glide.Glide
import com.hmyh.domain.ArticleListVO
import com.hmyh.hmyhnews.R
import com.hmyh.hmyhnews.databinding.ViewHolderInNewListBinding
import com.hmyh.hmyhnews.presentation.BaseViewHolder

class NewsListViewHolder(
    private val binding: ViewHolderInNewListBinding,
    val delegate: NewsListAdapter.Delegate
) : BaseViewHolder<ArticleListVO>(binding.root) {

    init {
        itemView.setOnClickListener {
            mData?.let { articleVO->
                delegate.onTapNewsItem(articleVO)
            }
        }
    }

    override fun bindData(data: ArticleListVO) {
        mData = data

        Glide.with(itemView.context)
            .load(data.urlToImage)
            .placeholder(R.drawable.dummy_image)
            .into(binding.ivNewsImageList)

        binding.tvNewsListTitle.text = data.title ?: ""
        binding.tvNewsListDescription.text = data.description ?: ""
    }
}