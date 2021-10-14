package com.example.walletapplicatiom.api.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.walletapplicatiom.api.repository.TransactionRepository

class TransactionViewModelFactory(private val transactionRepository: TransactionRepository): ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TransactionViewModel(transactionRepository) as T
    }
}