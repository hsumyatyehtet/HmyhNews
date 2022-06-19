package com.hmyh.hmyhnews.presentation.search

import com.bumptech.glide.Glide
import com.hmyh.domain.ArticleListVO
import com.hmyh.hmyhnews.R
import com.hmyh.hmyhnews.databinding.ViewHolderInSearchBinding
import com.hmyh.hmyhnews.presentation.BaseViewHolder

class NewsSearchViewHolder(
    private val binding: ViewHolderInSearchBinding
) : BaseViewHolder<ArticleListVO>(binding.root) {

    init {
        itemView.setOnClickListener {

        }
    }

    override fun bindData(data: ArticleListVO) {
        mData = data

        Glide.with(itemView.context)
            .load(data.urlToImage)
            .centerCrop()
            .into(binding.ivNewsSearchItem)

        binding.tvNewsSearchTitle.text = data.title ?: ""
    }
}