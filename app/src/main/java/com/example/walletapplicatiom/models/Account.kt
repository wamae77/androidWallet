package com.example.walletapplicatiom.models

import androidx.room.Entity


data class Account(
     val id: Int ,
     val accountNo: String,
     var customerId: String ,
     val balance: Double ,
     )