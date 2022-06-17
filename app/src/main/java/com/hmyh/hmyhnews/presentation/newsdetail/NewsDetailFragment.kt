package com.hmyh.news.presentation.newsdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.hmyh.hmyhnews.databinding.FragmentNewsDetailBinding

class NewsDetailFragment: Fragment() {

    private lateinit var binding: FragmentNewsDetailBinding

    private val args: NewsDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpDataObservation()
    }

    private fun setUpDataObservation() {
        var mAuthor = args.newsAuthor

        binding.tvAuthorName.text = mAuthor
    }

}