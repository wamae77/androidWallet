package com.example.walletapplicatiom.api.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.walletapplicatiom.api.repository.CustomerRepository

class MainViewModelFactory(private val customerRepository: CustomerRepository) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  MainActivityViewModel(customerRepository) as T
    }
}