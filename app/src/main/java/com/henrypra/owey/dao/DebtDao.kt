package com.henrypra.owey.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.henrypra.owey.model.Debt

@Dao
interface DebtDao {

    @Query("SELECT * FROM debt")
    fun getAllDebt(): List<Debt>

    @Query("SELECT * FROM debt WHERE id = :debtId")
    fun getSingleDebt(debtId: Int): Debt

    @Insert
    fun insertDebt(debt: Debt)

    @Query("DELETE FROM debt WHERE id = :debtId")
    fun deleteDebt(debtId: Int)

}