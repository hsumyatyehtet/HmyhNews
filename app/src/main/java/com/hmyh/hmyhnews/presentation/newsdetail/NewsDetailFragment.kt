package com.hmyh.news.presentation.newsdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.hmyh.hmyhnews.R
import com.hmyh.hmyhnews.databinding.FragmentNewsDetailBinding

class NewsDetailFragment : Fragment() {

    private lateinit var binding: FragmentNewsDetailBinding

    private val args: NewsDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpDataObservation()
    }

    @SuppressLint("SetTextI18n")
    private fun setUpDataObservation() {
        var mArticle = args.newsArticle

        binding.tvNewsDetailTitle.text = mArticle.title ?: ""
        binding.tvNewsDetailAuthorName.text = "By ${mArticle.author},"
        binding.tvNewsDetailSource.text = mArticle.source?.name ?: ""

        Glide.with(requireContext())
            .load(mArticle.urlToImage)
            .placeholder(R.drawable.dummy_image)
            .into(binding.ivNewsDetail)

        binding.tvNewsDetailContent.text = mArticle.description ?: ""

    }

}