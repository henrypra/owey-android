package com.henrypra.owey.feature.detail

import com.henrypra.owey.BaseActivity
import com.henrypra.owey.model.Debt
import com.henrypra.owey.room.AppDatabase
import com.henrypra.owey.room.RoomWrapper

class DetailPresenter(val activity: BaseActivity,
                      val id: Int,
                      val actionListener: DetailActionListener,
                      val view: DetailContract.View,
                      private val appDatabase: AppDatabase?) : DetailContract.Presenter {

    override fun retrieveDebtForId() {
        appDatabase?.let {

            RoomWrapper.getDebtForId(it, object : RoomWrapper.SingleDebtListener {
                override fun getSingleDebt(debt: Debt) {
                    view.displayDebt(debt)
                }
            }, id)
        }
    }

}