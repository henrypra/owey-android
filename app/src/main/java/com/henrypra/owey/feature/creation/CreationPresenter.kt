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

    override fun createDebt(amount: Double?, title: String?, friend: String, note: String?, isDebt: Boolean?) {
        appDatabase?.let { RoomWrapper.saveDebt(it, Debt(0, title = title, amount = amount, friend = friend, note = note, isDebt = isDebt, date = "0")) }
        actionListener.goToMainActivity()
    }

}