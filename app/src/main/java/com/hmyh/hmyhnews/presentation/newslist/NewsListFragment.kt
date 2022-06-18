package com.hmyh.hmyhnews.presentation.newslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hmyh.domain.ArticleListVO
import com.hmyh.hmyhnews.R
import com.hmyh.hmyhnews.databinding.FragmentNewListBinding
import com.hmyh.news.framework.getBundleNewsDetail
import com.hmyh.news.framework.getNewList

class NewsListFragment : Fragment(), NewsListAdapter.Delegate {

    private lateinit var mViewModel: NewListViewModel
    private lateinit var binding: FragmentNewListBinding

    private lateinit var mNewsListAdapter: NewsListAdapter

    var isListEndReached = false

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

        setUpViewModel()
        setUpRecyclerView()
        setUpListener()

        setUpOnUiReady()
        setUpDataObservation()
    }

    private fun setUpOnUiReady() {
        mViewModel.onUiReady()
    }

    private fun setUpViewModel(){
        mViewModel = ViewModelProviders.of(this)[NewListViewModel::class.java]
    }

    private fun setUpListener() {
        binding.rvNewsList.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = binding.rvNewsList.layoutManager!!.childCount
                val totalItemCount = binding.rvNewsList.layoutManager!!.itemCount
                val pastVisibleItems = (binding.rvNewsList.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if (visibleItemCount + pastVisibleItems < totalItemCount) {
                    isListEndReached = false
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && (recyclerView.layoutManager as LinearLayoutManager)
                        .findLastCompletelyVisibleItemPosition() == recyclerView.adapter!!.itemCount - 1
                    && !isListEndReached
                ) {
                    isListEndReached = true
                    mViewModel.loadMoreNewsList()
                }
            }
        })
    }

    private fun setUpDataObservation() {

        mViewModel.getNew().observe(viewLifecycleOwner, Observer {
            it?.let { news->
                news.articleList?.let { articleList->
                    mNewsListAdapter.setNewData(articleList)
                }
            }
        })

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