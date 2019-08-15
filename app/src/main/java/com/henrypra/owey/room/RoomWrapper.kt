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

    fun getDebtForId(appDatabase: AppDatabase, listener: SingleDebtListener, id: Int) {
        GlobalScope.launch {
            val debt = appDatabase.debtDao().getSingleDebt(id)
            listener.getSingleDebt(debt)
        }
    }

    fun saveDebt(appDatabase: AppDatabase, debt: Debt) {
        GlobalScope.launch {
            appDatabase.debtDao().insertDebt(debt)
        }
    }

    fun deleteDebtForId(appDatabase: AppDatabase, id: Int) {
        GlobalScope.launch {
            appDatabase.debtDao().deleteDebt(id)
        }
    }

    interface DebtListener {
        fun getAllDebts(debtList: List<Debt>)
    }

    interface SingleDebtListener {
        fun getSingleDebt(debt: Debt)
    }

}