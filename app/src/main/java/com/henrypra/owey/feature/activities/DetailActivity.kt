package com.henrypra.owey.feature.activities

import android.os.Bundle
import androidx.room.Room
import com.henrypra.owey.BaseActivity
import com.henrypra.owey.R
import com.henrypra.owey.feature.creation.CreationActionListener
import com.henrypra.owey.feature.creation.CreationFragment
import com.henrypra.owey.feature.creation.CreationPresenter
import com.henrypra.owey.feature.detail.DetailActionListener
import com.henrypra.owey.feature.detail.DetailFragment
import com.henrypra.owey.feature.detail.DetailPresenter
import com.henrypra.owey.room.AppDatabase
import com.henrypra.owey.utility.FragmentNavigation


class DetailActivity : BaseActivity(),
        CreationActionListener,
        DetailActionListener {

    var appDatabase: AppDatabase? = null

    private val fragmentNavigation: FragmentNavigation by lazy { FragmentNavigation(R.id.detail_fragment_container, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build()

        val intent = intent
        val message = intent.getStringExtra("CURRENT_FRAGMENT")
        val id: Int? = intent.extras.getInt("ID")
        when (message) {
            "CREATION" -> loadCreationFragment()
            "DETAIL" -> loadDetailFragment(id)
        }
    }

    private fun loadDetailFragment(id: Int?) {
        val detailFragment = DetailFragment()
        detailFragment.presenter = id?.let { DetailPresenter(this, it, this, detailFragment, appDatabase) }
        fragmentNavigation.replaceFragment(detailFragment)
    }

    private fun loadCreationFragment() {
        val creationFragment = CreationFragment()
        creationFragment.presenter = CreationPresenter(this, this, creationFragment, appDatabase)
        fragmentNavigation.replaceFragment(creationFragment)
    }

    override fun goToMainActivity() {
        finish()
    }

    companion object {
        private val DATABASE_NAME = "owey.db"
    }
}
