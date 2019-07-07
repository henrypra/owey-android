package com.henrypra.owey.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.henrypra.owey.model.Debt

@Dao
interface DebtDao {

    @Query("SELECT * FROM debt")
    fun getAllDebt(): List<Debt>

    @Insert
    fun insertDebt(debt: Debt)

    @Query("DELETE FROM debt")
    fun deleteAll()


}