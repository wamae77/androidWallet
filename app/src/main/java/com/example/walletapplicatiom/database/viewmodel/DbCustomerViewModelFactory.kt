package com.example.walletapplicatiom.database.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.walletapplicatiom.database.repository.DbCustomerRepository

class DbCustomerViewModelFactory(private val dbCustomerRepository: DbCustomerRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DbCustomerViewmodel(dbCustomerRepository) as T
    }
}