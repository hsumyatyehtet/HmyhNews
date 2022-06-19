package com.hmyh.hmyhnews.presentation.newslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hmyh.domain.ArticleListVO
import com.hmyh.hmyhnews.R
import com.hmyh.hmyhnews.databinding.FragmentNewListBinding
import com.hmyh.hmyhnews.domain.NewsListVO
import com.hmyh.hmyhnews.presentation.BaseFragment
import com.hmyh.news.framework.getBundleNewsDetail
import com.hmyh.news.framework.getNewList
import com.kaopiz.kprogresshud.KProgressHUD

class NewsListFragment : BaseFragment(){

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

    private fun setUpViewModel() {
        mViewModel = ViewModelProviders.of(this)[NewListViewModel::class.java]
    }

    private fun setUpListener() {

        binding.swipeRefreshNewList.setOnRefreshListener {
            binding.swipeRefreshNewList.isRefreshing = false
            setUpOnUiReady()
        }

        binding.ivSearch.setOnClickListener {
            findNavController().navigate(R.id.action_newsListFragment_to_searchFragment)
        }

        binding.rvNewsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = binding.rvNewsList.layoutManager!!.childCount
                val totalItemCount = binding.rvNewsList.layoutManager!!.itemCount
                val pastVisibleItems =
                    (binding.rvNewsList.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

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
            it?.let { news ->

                var mNewsList: MutableList<NewsListVO> = mutableListOf()
                var mArticleList: MutableList<ArticleListVO> = mutableListOf()

                mNewsList.addAll(news.distinctBy { it.newsId })

                mNewsList.forEach { news->
                    news.articleList?.let { article->
                        mArticleList.addAll(article.distinctBy { it.description })
                    }
                }

                mNewsListAdapter.setNewData(mArticleList)

            }
        })

        mViewModel.getShowOrHideProgress().observe(viewLifecycleOwner, Observer {
            it?.let { data ->
                if (data == 1) {
                    showProgressDialog()
                } else {
                    hideProgressDialog()
                }
            }
        })

        mViewModel.getErrorMessage().observe(viewLifecycleOwner, Observer {
            it?.let { errorMessage ->

                var mErrorMessage: String = ""

                if (errorMessage.contains("HTTP 429") || errorMessage.contains("HTTP 426")) {
                    mErrorMessage = "rateLimited"
                }

                if (mErrorMessage!=""){
                    binding.rvNewsList.visibility = View.GONE
                    binding.llNoResultContainer.visibility = View.VISIBLE
                    binding.tvErrorMessage.text = mErrorMessage
                }
                else{
                    binding.rvNewsList.visibility = View.VISIBLE
                    binding.llNoResultContainer.visibility = View.GONE
                }

            }
        })

        mViewModel.getErrorMessageMore().observe(viewLifecycleOwner, Observer {
            it?.let { errorMessageMore ->
                var mErrorMessageMore: String = ""
                if (errorMessageMore.contains("HTTP 429") || errorMessageMore.contains("HTTP 426")) {
                    mErrorMessageMore = "maximumResultsReached"
                }

                if (errorMessageMore !=""){
                    Toast.makeText(context,mErrorMessageMore,Toast.LENGTH_SHORT).show()
                }
            }
        })

        mViewModel.getNavigateSearchListToDetailData().observe(viewLifecycleOwner, Observer {article->
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                article?.let {article->
                    findNavController().navigate(
                        R.id.action_newsListFragment_to_newsDetailFragment,
                        getBundleNewsDetail(article)
                    )
                }
            }
        })

    }

    private fun setUpRecyclerView() {
        mNewsListAdapter = NewsListAdapter(mViewModel)
        binding.rvNewsList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvNewsList.adapter = mNewsListAdapter
    }
}