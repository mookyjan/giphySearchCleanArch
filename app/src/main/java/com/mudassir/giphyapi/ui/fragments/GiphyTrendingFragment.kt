package com.mudassir.giphyapi.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mudassir.core.Status
import com.mudassir.core.hideKeyboard
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.giphyapi.util.Constants.SAVED_QUERY_KEY
import com.mudassir.giphyapi.R
import com.mudassir.giphyapi.databinding.FragmentTrendingGiphyBinding
import com.mudassir.giphyapi.di.modules.GenericSavedStateViewModelFactory
import com.mudassir.giphyapi.di.modules.GiphyViewModelFactory
import com.mudassir.giphyapi.ui.adapter.GiphyTrendingAdapter
import com.mudassir.giphyapi.ui.viewModel.GiphyTrendingViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class GiphyTrendingFragment : Fragment(), MenuProvider, GiphyTrendingAdapter.Callbacks {

    val TAG = GiphyTrendingFragment.javaClass.name

    lateinit var mBinding: FragmentTrendingGiphyBinding

    @Inject
    internal lateinit var detailViewModelFactory: GiphyViewModelFactory

    private val viewModel: GiphyTrendingViewModel by viewModels {
        GenericSavedStateViewModelFactory(detailViewModelFactory, this)
    }
    private val giphyAdapter = GiphyTrendingAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
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
        val query = savedInstanceState?.getString(SAVED_QUERY_KEY) ?: ""
        Log.d(TAG, "onViewCreated: $query   ${viewModel.giphyLiveDataEvent.value}")
        observeEvents()
        uiSetup()
        initRecyclerView()
        //setup for the new menu options , the old one is deprecated
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SAVED_QUERY_KEY, viewModel.giphyLiveDataEvent.value.toString())
    }

    private fun uiSetup() {
        mBinding.lyOffline.btnRetry.setOnClickListener {
            viewModel.onEnter("")
        }
    }

    private fun observeEvents() {
        viewModel.onEnter()
        viewModel.giphyLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    Log.d(TAG, "observeEvents: Loading")
                    showProgressBar()
                }
                Status.SUCCESS -> {
                    hideProgressBar()
                    //hide the error layout
                    mBinding.lyOffline.root.visibility = View.GONE
                    Log.d(TAG, "observeEvents: Success")
                    giphyAdapter.submitList(it.data)
                }
                Status.EMPTY -> {
                    hideProgressBar()
                    Log.d(TAG, "observeEvents: Empty")
                    //show the error layout
                    mBinding.lyOffline.root.visibility = View.VISIBLE
                    mBinding.lyOffline.tvErrorDetail.text = getString(R.string.txt_no_result)
                }
                Status.ERROR -> {
                    hideProgressBar()
                    Log.d(TAG, "observeEvents: Error ${it.data}")
                    mBinding.lyOffline.root.visibility = View.VISIBLE
                    mBinding.lyOffline.tvErrorDetail.text = getString(R.string.txt_error_description)
                }
            }
        })
    }

    private fun showProgressBar() {
        mBinding.progressCircular.show()
    }

    private fun hideProgressBar() {
        mBinding.progressCircular.hide()
    }

    private fun initRecyclerView() {
        giphyAdapter.setupListener(this)
        mBinding.rvGiphyList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mBinding.rvGiphyList.adapter = giphyAdapter
    }

    companion object {
        fun newInstance() = GiphyTrendingFragment()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        // Add menu items here
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
        // below line is to get our menu item.
        val searchItem: MenuItem = menu.findItem(R.id.menu_search)
        // getting search view of our item.
        val searchView: SearchView = searchItem.actionView as SearchView
        val closeButton: ImageView =
            searchView.findViewById(androidx.appcompat.R.id.search_close_btn)

        searchView.setQuery(viewModel.giphyLiveDataEvent.value.toString(), false)
        searchView.clearFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView?.hideKeyboard()
                viewModel.onEnter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    searchView?.hideKeyboard()
                }
                return true
            }
        })

        closeButton.setOnClickListener {
            //when click on cross icon so reset to the trending api
            searchView.clearFocus()
            searchView.onActionViewCollapsed()
            viewModel.onEnter("")
        }

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        // Handle the menu selection
        return when (menuItem.itemId) {
            R.id.menu_search -> {
                // Do stuff...
                true
            }
            else -> false
        }
    }

    override fun onGiphyItemClick(view: View, item: GiphyDomainModel) {
        Log.d(TAG, "onGiphyItemClick: $item")
        viewModel.addToFavourite(item)
    }
}