package com.henrypra.owey.feature.main

import android.os.Bundle
import com.henrypra.owey.BaseActivity
import com.henrypra.owey.R
import com.henrypra.owey.feature.login.LoginActionListener
import com.henrypra.owey.feature.login.LoginFragment
import com.henrypra.owey.feature.login.LoginPresenter
import com.henrypra.owey.utility.FragmentNavigation

class MainActivity : BaseActivity(),
        MainActionListener,
        LoginActionListener {

    private val fragmentNavigation: FragmentNavigation by lazy { FragmentNavigation(R.id.main_fragment_container, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadLoginFragment()
    }

    private fun loadLoginFragment() {
        val loginFragment = LoginFragment()
        loginFragment.presenter = LoginPresenter(this, this, loginFragment)
        fragmentNavigation.replaceFragment(loginFragment)
    }

    private fun loadMainFragment() {
        val mainFragment = MainFragment()
        mainFragment.presenter = MainPresenter(this, this, mainFragment)
        fragmentNavigation.replaceFragment(mainFragment)
    }
}
