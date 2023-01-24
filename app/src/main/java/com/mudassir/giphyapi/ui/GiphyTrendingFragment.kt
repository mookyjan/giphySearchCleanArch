package com.mudassir.giphyapi.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mudassir.core.Resource
import com.mudassir.core.Status
import com.mudassir.giphyapi.R
import com.mudassir.giphyapi.databinding.FragmentTrendingGiphyBinding
import com.mudassir.giphyapi.ui.adapter.GiphyTrendingAdapter
import dagger.android.AndroidInjection
import dagger.android.DaggerFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class GiphyTrendingFragment : Fragment() {

    val TAG = GiphyTrendingFragment.javaClass.name

    lateinit var mBinding: FragmentTrendingGiphyBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: GiphyTrendingViewModel by viewModels { viewModelFactory }
    val giphyAdapter = GiphyTrendingAdapter()

    override fun onAttach(context: Context) {

        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentTrendingGiphyBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)
        observeEvents()
        initRecyclerView()
    }

    private fun observeEvents(){

        viewModel.callApi()

        viewModel.giphyLiveData.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.LOADING ->{
                    Log.d(TAG, "observeEvents: Loading")
                }
                Status.SUCCESS -> {
                    Log.d(TAG, "observeEvents: Success")
                  giphyAdapter.submitList(it.data)
                }
                Status.EMPTY ->{
                    Log.d(TAG, "observeEvents: Empty")
                }
                Status.ERROR -> {
                    Log.d(TAG, "observeEvents: Error")
                }
            }
        })
    }

    private fun initRecyclerView() {
        mBinding.rvGiphyList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mBinding.rvGiphyList.adapter = giphyAdapter
    }

    companion object {
        fun newInstance() = GiphyTrendingFragment()
    }
}