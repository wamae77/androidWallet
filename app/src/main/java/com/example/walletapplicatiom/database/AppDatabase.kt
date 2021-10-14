package com.example.walletapplicatiom.database

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.walletapplicatiom.models.Customer
import com.example.walletapplicatiom.models.Transaction

@Database(entities = [Customer::class,Transaction::class],version = 1,exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun customerDao() :CustomerDao
    abstract fun transactionDao():TransactionDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase?=null
        fun getAppDatabase(contex:Context): AppDatabase{
            val templateInstance = INSTANCE
            if (templateInstance != null){
                return templateInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    contex.applicationContext,
                    AppDatabase::class.java,
                    "appdatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}