package com.hmyh.hmyhnews.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmyh.hmyhnews.databinding.FragmentSearchBinding
import com.hmyh.hmyhnews.presentation.BaseFragment
import com.hmyh.news.framework.getNewList

class SearchFragment: BaseFragment() {

    private lateinit var binding: FragmentSearchBinding

    private lateinit var mAdapter: NewsSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        setUpDataObservation()
    }

    private fun setUpDataObservation() {
        var mNew = getNewList()

        mNew.articleList?.let { articleList->
            mAdapter.setNewData(articleList)
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = NewsSearchAdapter()
        binding.rvNewsSearch.layoutManager =
            LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.rvNewsSearch.adapter = mAdapter
    }

}