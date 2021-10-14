package com.example.walletapplicatiom.database.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.walletapplicatiom.database.AppDatabase
import com.example.walletapplicatiom.database.CustomerDao
import com.example.walletapplicatiom.database.repository.DbCustomerRepository
import com.example.walletapplicatiom.models.Customer
import com.example.walletapplicatiom.models.Login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DbCustomerViewmodel(private val dbCustomerRepository: DbCustomerRepository):ViewModel() {
    val cust: MutableLiveData<Customer> = MutableLiveData()

    fun getUserVM(context: Context,userId: String){
        viewModelScope.launch {
            val u = dbCustomerRepository.getCustomer(context,userId)
            cust.value = u
        }
    }
    fun addCustomerVM(context: Context,customer: Customer){
        viewModelScope.launch(Dispatchers.IO) {
            dbCustomerRepository.addCustomer(context,customer)
        }
    }

}