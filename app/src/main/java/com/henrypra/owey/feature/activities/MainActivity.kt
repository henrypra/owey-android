package com.henrypra.owey.feature.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.room.Room
import com.henrypra.owey.BaseActivity
import com.henrypra.owey.R
import com.henrypra.owey.feature.main.MainActionListener
import com.henrypra.owey.feature.main.MainFragment
import com.henrypra.owey.feature.main.MainPresenter
import com.henrypra.owey.room.AppDatabase
import com.henrypra.owey.utility.FragmentNavigation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),
        MainActionListener,
        View.OnClickListener {
    var appDatabase: AppDatabase? = null

    private val fragmentNavigation: FragmentNavigation by lazy { FragmentNavigation(R.id.main_fragment_container, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build()
        loadMainFragment()
        initOnCLickListeners()
    }

    private fun initOnCLickListeners() {
        fab?.setOnClickListener(this)
    }

    private fun loadMainFragment() {
        val mainFragment = MainFragment()
        mainFragment.presenter = MainPresenter(this, this, mainFragment, appDatabase)
        fragmentNavigation.replaceFragment(mainFragment)
    }

    private fun loadDetailActivity(fragment: String) {
        intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("CURRENT_FRAGMENT", fragment)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            //TODO add enum
            R.id.fab -> loadDetailActivity("CREATION")

        }
    }

    companion object {
        private val DATABASE_NAME = "owey.db"
    }
}
