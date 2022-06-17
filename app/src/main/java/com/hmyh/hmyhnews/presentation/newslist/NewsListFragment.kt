package com.hmyh.hmyhnews.presentation.newslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmyh.domain.ArticleListVO
import com.hmyh.hmyhnews.R
import com.hmyh.hmyhnews.databinding.FragmentNewListBinding
import com.hmyh.news.framework.getBundleNewsDetail
import com.hmyh.news.framework.getNewList

class NewsListFragment : Fragment(), NewsListAdapter.Delegate {

    private lateinit var binding: FragmentNewListBinding

    private lateinit var mNewsListAdapter: NewsListAdapter

    private var mArticleList: MutableList<ArticleListVO> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpDataObservation()
        setUpRecyclerView()
        setUpData()
    }

    private fun setUpData() {
        mNewsListAdapter.setNewData(mArticleList)
    }

    private fun setUpDataObservation() {
        var mNews = getNewList()

        mNews.articleList?.let { articleList ->
            mArticleList = articleList
        }
    }

    private fun setUpRecyclerView() {
        mNewsListAdapter = NewsListAdapter(this)
        binding.rvNewsList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvNewsList.adapter = mNewsListAdapter
    }

    override fun onTapNewsItem(author: com.hmyh.domain.ArticleListVO) {



        findNavController().navigate(
            R.id.action_newsListFragment_to_newsDetailFragment,
            getBundleNewsDetail(author)
        )
    }

}