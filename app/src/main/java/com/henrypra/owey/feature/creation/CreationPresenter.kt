package com.henrypra.owey.feature.creation

import com.henrypra.owey.BaseActivity
import com.henrypra.owey.model.Debt
import com.henrypra.owey.room.AppDatabase
import com.henrypra.owey.room.RoomWrapper

class CreationPresenter(val activity: BaseActivity,
                        val actionListener: CreationActionListener,
                        val view: CreationContract.View,
                        private val appDatabase: AppDatabase?) : CreationContract.Presenter {
    lateinit var debt: Debt

    override fun createDebt(amount: Double) {
        appDatabase?.let { RoomWrapper.saveDebt(it, Debt(0, title = "Mittagessen", date = "7.7.2019", desc = "Du schuldest Geld", amount = amount)) }
        actionListener.goToMainActivity()
    }
}