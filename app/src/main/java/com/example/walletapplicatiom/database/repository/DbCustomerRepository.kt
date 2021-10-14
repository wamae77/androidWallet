package com.example.walletapplicatiom.database.repository

import android.content.Context
import com.example.walletapplicatiom.database.AppDatabase
import com.example.walletapplicatiom.database.CustomerDao
import com.example.walletapplicatiom.models.Customer


class DbCustomerRepository{

     suspend fun addCustomer(context: Context,customer: Customer){
        AppDatabase.getAppDatabase(context).customerDao().insertCustomer(customer)
    }

    suspend fun getCustomer(context: Context,userId: String):Customer{
        return   AppDatabase.getAppDatabase(context).customerDao().getCustomer()
    }
}