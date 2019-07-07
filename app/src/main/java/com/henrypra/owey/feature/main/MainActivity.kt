package com.henrypra.owey.feature.main

import android.os.Bundle
import androidx.room.Room
import com.henrypra.owey.BaseActivity
import com.henrypra.owey.R
import com.henrypra.owey.room.AppDatabase
import com.henrypra.owey.utility.FragmentNavigation

class MainActivity : BaseActivity(),
        MainActionListener {

    var appDatabase: AppDatabase? = null

    private val fragmentNavigation: FragmentNavigation by lazy { FragmentNavigation(R.id.main_fragment_container, this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build()
        loadMainFragment()
    }

    private fun loadMainFragment() {
        val mainFragment = MainFragment()
        mainFragment.presenter = MainPresenter(this, this, mainFragment, appDatabase)
        fragmentNavigation.replaceFragment(mainFragment)
    }

    companion object {
        private val DATABASE_NAME = "owey.db"
    }
}
