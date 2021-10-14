package com.example.walletapplicatiom.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id :Int,
    @ColumnInfo(name = "customerId")
    var customerId: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "firstName")
    val firstName: String,
    @ColumnInfo(name = "lastName")
    val lastName: String,
    @ColumnInfo(name = "pin")
    val pin: String
)