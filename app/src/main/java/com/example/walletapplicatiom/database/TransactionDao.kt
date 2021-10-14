package com.example.walletapplicatiom.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.walletapplicatiom.models.Customer
import com.example.walletapplicatiom.models.Transaction

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCustomer(transaction: Transaction)

    @Query("SELECT * FROM `transaction`")
    suspend fun getAllTransactions():List<Transaction>
}