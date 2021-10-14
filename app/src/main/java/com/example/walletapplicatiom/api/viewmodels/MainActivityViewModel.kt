package com.example.walletapplicatiom.api.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walletapplicatiom.models.Customer
import com.example.walletapplicatiom.models.Login
import com.example.walletapplicatiom.api.repository.CustomerRepository
import com.example.walletapplicatiom.models.Account
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivityViewModel(private val  customerRepository: CustomerRepository) : ViewModel() {
    val response: MutableLiveData<Response<JsonObject>> = MutableLiveData()
    val balance: MutableLiveData<Response<Account>> = MutableLiveData()

    fun loginCustomerVM(login: Login){
        viewModelScope.launch {
            val resp = customerRepository.loginCustomerRepo(login)
            response.value = resp
        }
    }

    fun accountBalanceVm(account: Account){
        viewModelScope.launch {
            val response = customerRepository.getAccountBalanceRepo(account)
            balance.value=response
        }
    }
}