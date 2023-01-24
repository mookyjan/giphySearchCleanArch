package com.mudassir.giphyapi.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mudassir.core.Status
import com.mudassir.core.hideKeyboard
import com.mudassir.giphyapi.R
import com.mudassir.giphyapi.databinding.FragmentTrendingGiphyBinding
import com.mudassir.giphyapi.ui.adapter.GiphyTrendingAdapter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class GiphyTrendingFragment : Fragment(), MenuProvider {

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
        //setup for the new menu options , the old one is deprecated
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun observeEvents() {
        viewModel.giphyLiveDataEvent.value = viewModel.query.value
//        viewModel.getGiphyList("")
        viewModel.giphyLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    Log.d(TAG, "observeEvents: Loading")
                    showProgressBar()
                }
                Status.SUCCESS -> {
                    hideProgressBar()
                    Log.d(TAG, "observeEvents: Success")
                    giphyAdapter.submitList(it.data)
                }
                Status.EMPTY -> {
                    hideProgressBar()
                    Log.d(TAG, "observeEvents: Empty")
                }
                Status.ERROR -> {
                    hideProgressBar()
                    Log.d(TAG, "observeEvents: Error")
                }
            }
        })
    }

    private fun showProgressBar() {
        mBinding.progressCircular.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        mBinding.progressCircular.visibility = View.GONE
    }

    private fun initRecyclerView() {
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

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.hideKeyboard()
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
}