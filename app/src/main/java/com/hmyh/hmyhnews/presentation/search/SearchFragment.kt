package com.hmyh.hmyhnews.presentation.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hmyh.domain.ArticleListVO
import com.hmyh.hmyhnews.databinding.FragmentSearchBinding
import com.hmyh.hmyhnews.presentation.BaseFragment
import com.hmyh.news.framework.getNewList

class SearchFragment : BaseFragment() {

    private lateinit var mViewModel: SearchListViewModel
    private lateinit var binding: FragmentSearchBinding

    private lateinit var mAdapter: NewsSearchAdapter

    var isListEndReached = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel()
        setUpRecyclerView()
        setUpListener()

        setUpDataObservation()
    }

    private fun setUpListener() {
        val handler = Handler(Looper.getMainLooper())
        binding.etNewsSearch.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                handler.removeCallbacksAndMessages(null)
                handler.postDelayed({
                    s?.toString()?.let { searchWord ->
                        onChangeTextAfterSecond(searchWord)
                    }
                }, 600)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
    }

    private fun onChangeTextAfterSecond(searchWord: String) {
        mViewModel.loadSearchNews(searchWord)

        binding.rvNewsSearch.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = binding.rvNewsSearch.layoutManager!!.childCount
                val totalItemCount = binding.rvNewsSearch.layoutManager!!.itemCount
                val pastVisibleItems =
                    (binding.rvNewsSearch.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

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
                    mViewModel.loadMoreSearchNews(searchWord)

                }
            }

        })
    }

    private fun setUpViewModel() {
        mViewModel = ViewModelProviders.of(this)[SearchListViewModel::class.java]
    }

    private fun setUpDataObservation() {

        mViewModel.getSearchNewsList().observe(viewLifecycleOwner, Observer {
            it?.let { articleList->
                mAdapter.setNewData(articleList as MutableList<ArticleListVO>)
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

                if (errorMessage.contains("HTTP 429") || errorMessage.contains("HTTP 426")
                    || errorMessage.contains("HTTP 400")) {
                    mErrorMessage = "No Rsults"
                }
                else if (errorMessage.contains("HTTP 400")){
                    mErrorMessage = "No Results"
                }

                if (mErrorMessage!=""){
                    binding.rvNewsSearch.visibility = View.GONE
                    binding.llNoResultContainer.visibility = View.VISIBLE
                    binding.tvErrorMessage.text = mErrorMessage
                }
                else{
                    binding.rvNewsSearch.visibility = View.VISIBLE
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
                    Toast.makeText(context,mErrorMessageMore, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun setUpRecyclerView() {
        mAdapter = NewsSearchAdapter()
        binding.rvNewsSearch.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvNewsSearch.adapter = mAdapter
    }

}