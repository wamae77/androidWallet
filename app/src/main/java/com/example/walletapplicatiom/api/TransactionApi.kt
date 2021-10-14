package com.example.walletapplicatiom.api

import com.example.walletapplicatiom.models.Transaction
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.*

interface TransactionApi {

    @POST("transactions/send-money")
    suspend fun sendMoney(
        @Body jsonObject: JsonObject
    ):Response<JsonObject>

    @POST("transactions/last-100-transactions")
    suspend fun getTransactions(
        @Body jsonObject: JsonObject
    ):Response<List<Transaction>>
}