package com.mudassir.giphyapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.mudassir.giphyapi.R
import com.mudassir.giphyapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            setUpNavigation(navHostFragment)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setUpNavigation(supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
    }


    private fun setUpNavigation(navFragment: NavHostFragment) {
        NavigationUI.setupWithNavController(
            binding.bottomNavigation,
            navFragment.navController
        )
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
}