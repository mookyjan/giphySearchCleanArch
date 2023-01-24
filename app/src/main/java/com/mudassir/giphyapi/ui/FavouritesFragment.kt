package com.mudassir.giphyapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mudassir.giphyapi.R
import com.mudassir.giphyapi.databinding.FragmentFavouritesBinding

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
open class FavouritesFragment : Fragment() {


    private var mBinding: FragmentFavouritesBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentFavouritesBinding.inflate(inflater,container,false)
        return mBinding?.root
    }
}