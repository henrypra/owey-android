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
import com.henrypra.owey.views.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(),
        MainActionListener,
        CategoryActionListener,
        View.OnClickListener {

    var appDatabase: AppDatabase? = null

    private val fragmentNavigation: FragmentNavigation by lazy { FragmentNavigation(R.id.container, this) }

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
        adapter.addFragment(mainFragment)

        val debtFragment = CategoryFragment()
        debtFragment.presenter = CategoryPresenter(this, this, debtFragment, true, appDatabase)
        adapter.addFragment(debtFragment)

        val loanFragment = CategoryFragment()
        loanFragment.presenter = CategoryPresenter(this, this, loanFragment, false, appDatabase)
        adapter.addFragment(loanFragment)

        pager?.adapter = adapter

        pager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))

        pager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                lateinit var appTitle: String
                when (position) {
                    0 -> appTitle = getString(R.string.app_name) + getString(R.string.tab_home)
                    1 -> appTitle = getString(R.string.app_name) + getString(R.string.tab_debts)
                    2 -> appTitle = getString(R.string.app_name) + getString(R.string.tab_loans)
                }
                tv_app_title?.text = appTitle
            }
        })
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(pager))
    }

    private fun initOnCLickListeners() {
        fab?.setOnClickListener(this)
    }

    private fun loadMainFragment() {
        val mainFragment = MainFragment()
        mainFragment.presenter = MainPresenter(this, this, mainFragment, appDatabase)
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
