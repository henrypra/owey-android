package com.henrypra.owey.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val fragments = ArrayList<Fragment>()

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }
}