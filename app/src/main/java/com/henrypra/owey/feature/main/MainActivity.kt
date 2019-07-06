package com.henrypra.owey.feature.main

import android.os.Bundle
import com.henrypra.owey.BaseActivity
import com.henrypra.owey.R
import com.henrypra.owey.utility.FragmentNavigation

class MainActivity : BaseActivity(),
        MainActionListener {

    private val fragmentNavigation: FragmentNavigation by lazy { FragmentNavigation(R.id.main_fragment_container, this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMainFragment()
    }

    private fun loadMainFragment() {
        val mainFragment = MainFragment()
        mainFragment.presenter = MainPresenter(this, this, mainFragment)
        fragmentNavigation.replaceFragment(mainFragment)
    }

}
