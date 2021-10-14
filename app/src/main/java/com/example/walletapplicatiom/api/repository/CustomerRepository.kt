package com.example.walletapplicatiom.api.repository

import com.example.walletapplicatiom.api.RetrofitInstance
import com.example.walletapplicatiom.models.Account
import com.example.walletapplicatiom.models.Customer
import com.example.walletapplicatiom.models.Login
import com.google.gson.JsonObject
import retrofit2.Response

class CustomerRepository {
    suspend fun loginCustomerRepo(login: Login):Response<JsonObject>{
       return RetrofitInstance.customerApi.loginCustomer(login)
    }
    suspend fun getAccountBalanceRepo(account:Account):Response<Account>{
        return RetrofitInstance.customerApi.getBalance(account)
    }
}