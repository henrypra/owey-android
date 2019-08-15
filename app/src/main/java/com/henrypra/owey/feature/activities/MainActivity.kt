package com.henrypra.owey.feature.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.room.Room
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.henrypra.owey.BaseActivity
import com.henrypra.owey.R
import com.henrypra.owey.feature.category.CategoryActionListener
import com.henrypra.owey.feature.category.CategoryFragment
import com.henrypra.owey.feature.category.CategoryPresenter
import com.henrypra.owey.feature.main.MainActionListener
import com.henrypra.owey.feature.main.MainFragment
import com.henrypra.owey.feature.main.MainPresenter
import com.henrypra.owey.room.AppDatabase
import com.henrypra.owey.utility.FragmentNavigation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),
        MainActionListener,
        CategoryActionListener,
        View.OnClickListener {

    var appDatabase: AppDatabase? = null

    private val fragmentNavigation: FragmentNavigation by lazy { FragmentNavigation(R.id.main_fragment_container, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build()
        loadMainFragment()
        initOnCLickListeners()
        setupViewPager(container)

    }

    private fun setupViewPager(pager: ViewPager?) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        val mainFragment = MainFragment()
        mainFragment.presenter = MainPresenter(this, this, mainFragment, appDatabase)
        adapter.addFragment(mainFragment, "Home")

        val debtFragment = CategoryFragment()
        debtFragment.presenter = CategoryPresenter(this, this, debtFragment, appDatabase)
        adapter.addFragment(debtFragment, "Debt")

        val loanFragment = CategoryFragment()
        loanFragment.presenter = CategoryPresenter(this, this, loanFragment, appDatabase)
        adapter.addFragment(loanFragment, "Loans")

        pager?.adapter = adapter

        pager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(pager))
    }

    private fun initOnCLickListeners() {
        fab?.setOnClickListener(this)
    }

    private fun loadMainFragment() {
        val mainFragment = CategoryFragment()
        mainFragment.presenter = CategoryPresenter(this, this, mainFragment, appDatabase)
        fragmentNavigation.replaceFragment(mainFragment)
    }

    private fun loadDetailActivity(fragment: String, id: Int?) {
        intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("CURRENT_FRAGMENT", fragment)
        intent.putExtra("ID", id)
        startActivity(intent)
    }

    override fun onGoToDetailDebt(id: Int) {
        loadDetailActivity("DETAIL", id)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            //TODO add enum
            R.id.fab -> loadDetailActivity("CREATION", null)

        }
    }

    companion object {
        private val DATABASE_NAME = "owey.db"
    }
}
