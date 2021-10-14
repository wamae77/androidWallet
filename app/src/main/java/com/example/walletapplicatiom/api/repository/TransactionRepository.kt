package com.example.walletapplicatiom.api.repository

import com.example.walletapplicatiom.api.RetrofitInstance
import com.example.walletapplicatiom.models.Transaction
import com.google.gson.JsonObject
import retrofit2.Response

class TransactionRepository {
    suspend fun sendMoneyRepo(jsonObject: JsonObject): Response<JsonObject> {
        return RetrofitInstance.transactionApi.sendMoney(jsonObject)
    }

    suspend fun getTransactions(jsonObject: JsonObject):Response<List<Transaction>>{
        return RetrofitInstance.transactionApi.getTransactions(jsonObject)
    }
}