package com.example.walletapplicatiom.database.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.walletapplicatiom.database.repository.DbTransRepository

class DbTransVmFactory(private val dbTransRepository: DbTransRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DbTransViewModel(dbTransRepository) as T
    }
}