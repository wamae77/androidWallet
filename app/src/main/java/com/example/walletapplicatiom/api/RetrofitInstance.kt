package com.example.walletapplicatiom.api

import com.example.walletapplicatiom.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val customerApi: CustomerApi by lazy {
        retrofit.create(CustomerApi::class.java)
    }
    val transactionApi: TransactionApi by lazy {
        retrofit.create(TransactionApi::class.java)
    }
}