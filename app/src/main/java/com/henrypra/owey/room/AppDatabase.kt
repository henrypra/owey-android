package com.henrypra.owey.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.henrypra.owey.dao.DebtDao
import com.henrypra.owey.model.Debt

@Database(entities = [Debt::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun debtDao(): DebtDao
}