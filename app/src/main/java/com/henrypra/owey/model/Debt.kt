package com.henrypra.owey.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Debt(@PrimaryKey(autoGenerate = true) val id: Int,
                val title: String?,
                val amount: Double?,
                val currency: String?,
                val friend: String,
                val note: String?,
                val isDebt: Boolean?,
                val date: String)