package com.mudassir.giphyapi.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mudassir.core.Status
import com.mudassir.giphyapi.databinding.FragmentFavouritesBinding
import com.mudassir.giphyapi.di.modules.GenericSavedStateViewModelFactory
import com.mudassir.giphyapi.di.modules.GiphyViewModelFactory
import com.mudassir.giphyapi.ui.adapter.FavouriteAdapter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class FavouritesFragment : Fragment() {

    val TAG = GiphyTrendingFragment.javaClass.name
    private var mBinding: FragmentFavouritesBinding? = null

    @Inject
    internal lateinit var detailViewModelFactory: GiphyViewModelFactory

    private val viewModel: GiphyTrendingViewModel by viewModels {
        GenericSavedStateViewModelFactory(detailViewModelFactory, this)
    }

    private val favouriteListAdapter = FavouriteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        setMenuVisibility(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.addToFavouriteEvent.value = Unit
        observeEvents()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mBinding?.rvFavouriteList?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mBinding?.rvFavouriteList?.adapter = favouriteListAdapter
    }

    private fun observeEvents() {
        viewModel.getFavList().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    Log.d(TAG, "observeEvents: Loading")
                    showProgressBar()
                }
                Status.SUCCESS -> {
                    hideProgressBar()
                    Log.d(TAG, "observeEvents: Success ${it.data?.size}")
                    favouriteListAdapter.submitList(it.data)
                }
                Status.EMPTY -> {
                    hideProgressBar()
                    Log.d(TAG, "observeEvents: Empty")
                }
                Status.ERROR -> {
                    hideProgressBar()
                    Log.d(TAG, "observeEvents: Error ${it.data}")
                }
            }
        })
    }

    private fun showProgressBar() {
        mBinding?.progressCircular?.show()
    }

    private fun hideProgressBar() {
        mBinding?.progressCircular?.hide()
    }

}