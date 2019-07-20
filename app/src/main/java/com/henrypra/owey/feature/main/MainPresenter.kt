package com.henrypra.owey.feature.main

import com.henrypra.owey.BaseActivity
import com.henrypra.owey.model.Debt
import com.henrypra.owey.room.AppDatabase
import com.henrypra.owey.room.RoomWrapper

class MainPresenter(val activity: BaseActivity,
                    val actionListener: MainActionListener,
                    val view: MainContract.View,
                    private val appDatabase: AppDatabase?) : MainContract.Presenter {

    override fun retrieveDebtFromDatabase() {
        appDatabase?.let {
            RoomWrapper.getAllDebts(it, object : RoomWrapper.DebtListener {
                override fun getAllDebts(debtList: List<Debt>) {
                    view.displayDebtList(debtList)
                }
            })
        }
    }


}