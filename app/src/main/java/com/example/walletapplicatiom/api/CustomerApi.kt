package com.example.walletapplicatiom.api

import com.example.walletapplicatiom.models.Account
import com.example.walletapplicatiom.models.Customer
import com.example.walletapplicatiom.models.Login
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CustomerApi {

    @POST("customers/login")
    suspend fun loginCustomer(
        @Body login: Login
    ): Response<JsonObject>

    @POST("accounts/balance")
    suspend fun getBalance(
        @Body account: Account
    ): Response<Account>

}