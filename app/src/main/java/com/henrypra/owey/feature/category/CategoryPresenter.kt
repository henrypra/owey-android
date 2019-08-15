package com.henrypra.owey.feature.category

import com.henrypra.owey.BaseActivity
import com.henrypra.owey.model.Debt
import com.henrypra.owey.room.AppDatabase
import com.henrypra.owey.room.RoomWrapper

class CategoryPresenter(val activity: BaseActivity,
                        val actionListener: CategoryActionListener,
                        val view: CategoryContract.View,
                        val isDebtList: Boolean,
                        private val appDatabase: AppDatabase?) : CategoryContract.Presenter {

    override fun retrieveDebtFromDatabase() {
        appDatabase?.let {
            RoomWrapper.getAllDebts(it, object : RoomWrapper.DebtListener {
                override fun getAllDebts(debtList: List<Debt>) {
                    if (isDebtList) {
                        view.displayDebtList(debtList.filter { debt -> debt.isDebt == true })
                    } else {
                        view.displayDebtList(debtList.filter { debt -> debt.isDebt == false })
                    }
                }
            })
        }
    }

    override fun onDebtClicked(id: Int) {
        actionListener.onGoToDetailDebt(id)
    }

}