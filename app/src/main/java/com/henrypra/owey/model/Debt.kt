package com.henrypra.owey.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Debt(@PrimaryKey(autoGenerate = true) val id: Int,
                val title: String?,
                val date: String,
                @ColumnInfo(name = "description") val desc: String?,
                val amount: Double)