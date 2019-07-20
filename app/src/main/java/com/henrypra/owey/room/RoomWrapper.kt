package com.henrypra.owey.room

import com.henrypra.owey.model.Debt
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object RoomWrapper {

    fun getAllDebts(appDatabase: AppDatabase, listener: DebtListener) {
        GlobalScope.launch {
            val debtList = appDatabase.debtDao().getAllDebt()
            listener.getAllDebts(debtList)
        }
    }

    fun saveDebt(appDatabase: AppDatabase, debt: Debt) {
        GlobalScope.launch {
            appDatabase.debtDao().insertDebt(debt)
        }
    }

    interface DebtListener {
        fun getAllDebts(debtList: List<Debt>)
    }

}