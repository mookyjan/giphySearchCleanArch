package com.mudassir.giphyapi.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mudassir.core.hide
import com.mudassir.core.show
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.giphyapi.databinding.FragmentFavouritesBinding
import com.mudassir.giphyapi.di.modules.GenericSavedStateViewModelFactory
import com.mudassir.giphyapi.di.modules.GiphyViewModelFactory
import com.mudassir.giphyapi.ui.adapter.FavouriteAdapter
import com.mudassir.giphyapi.ui.viewModel.GiphyTrendingViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class FavouritesFragment : Fragment(), FavouriteAdapter.Callbacks {

    private val TAG: String = GiphyTrendingFragment.Companion::class.java.name
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
        observeEvents()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        favouriteListAdapter.setupListener(this)
        mBinding?.rvFavouriteList?.layoutManager =
            GridLayoutManager(context, 2)
        mBinding?.rvFavouriteList?.adapter = favouriteListAdapter
    }

    private fun observeEvents() {
        viewModel.favouriteList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "observeEvents: Success ${it?.size}")
            if (it.isEmpty()) {
                showErrorText()
            } else {
                hideErrorText()
                favouriteListAdapter.submitList(it)
            }
        })
    }
    private fun showErrorText() {
        mBinding?.rvFavouriteList?.hide()
        mBinding?.tvEmptyText?.show()
    }

    private fun hideErrorText() {
        mBinding?.rvFavouriteList?.show()
        mBinding?.tvEmptyText?.hide()
    }

    override fun onGiphyItemClick(view: View, item: GiphyDomainModel) {
        viewModel.removeFromFavourite(item)
    }
}