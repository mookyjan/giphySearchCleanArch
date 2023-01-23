package com.mudassir.giphyapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mudassir.giphyapi.R
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [GiphyTrendingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GiphyTrendingFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trending_giphy, container, false)
    }

    companion object {

        fun newInstance() = GiphyTrendingFragment()
    }
}