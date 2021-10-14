package com.example.walletapplicatiom.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "accountNo")
    val accountNo: String,
    @ColumnInfo(name = "amount")
    val amount: String,
)