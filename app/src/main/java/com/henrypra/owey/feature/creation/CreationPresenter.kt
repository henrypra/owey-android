package com.henrypra.owey.feature.creation

import com.henrypra.owey.BaseActivity
import com.henrypra.owey.feature.main.MainContract
import com.henrypra.owey.room.AppDatabase

class CreationPresenter(val activity: BaseActivity,
                        val actionListener: CreationActionListener,
                        val view: CreationContract.View,
                        private val appDatabase: AppDatabase?) : CreationContract.Presenter {


}