package com.hmyh.hmyhnews.presentation.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
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

    }

    private fun setUpRecyclerView() {
        mAdapter = NewsSearchAdapter()
        binding.rvNewsSearch.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvNewsSearch.adapter = mAdapter
    }

}