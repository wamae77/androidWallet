package com.example.walletapplicatiom.database.repository

import android.content.Context
import com.example.walletapplicatiom.database.AppDatabase
import com.example.walletapplicatiom.models.Transaction

class DbTransRepository {
    suspend fun addTransaction(context: Context,transaction: Transaction){
        AppDatabase.getAppDatabase(context).transactionDao().insertCustomer(transaction)
    }

    suspend fun getTransactionsRepo(context: Context):List<Transaction>{
        return AppDatabase.getAppDatabase(context).transactionDao().getAllTransactions()
    }
}