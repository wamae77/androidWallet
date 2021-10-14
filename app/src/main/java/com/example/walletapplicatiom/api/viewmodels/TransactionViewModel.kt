package com.example.walletapplicatiom.api.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walletapplicatiom.api.repository.TransactionRepository
import com.example.walletapplicatiom.models.Transaction
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Response

class TransactionViewModel(private val transactionRepository: TransactionRepository):ViewModel() {

    val response:MutableLiveData<Response<JsonObject>> = MutableLiveData()
    val transactionsList : MutableLiveData<Response<List<Transaction>>> = MutableLiveData()

    fun sendMoneyVm(jsonObject: JsonObject){
        viewModelScope.launch {
            val resp = transactionRepository.sendMoneyRepo(jsonObject)
            response.value = resp
        }
    }

    fun getTransactions(jsonObject: JsonObject){
        viewModelScope.launch {
            val resp = transactionRepository.getTransactions(jsonObject)
            transactionsList.value = resp
        }
    }

}