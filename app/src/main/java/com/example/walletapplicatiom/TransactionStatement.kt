package com.example.walletapplicatiom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.walletapplicatiom.databinding.ActivityHomepageBinding
import com.example.walletapplicatiom.databinding.ActivityTransactioStatementBinding
import android.R
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

import android.widget.TextView

import android.view.LayoutInflater
import android.view.View

import android.widget.TableLayout
import androidx.lifecycle.ViewModelProvider
import com.example.walletapplicatiom.api.repository.TransactionRepository
import com.example.walletapplicatiom.api.viewmodels.TransactionViewModel
import com.example.walletapplicatiom.api.viewmodels.TransactionViewModelFactory
import com.google.gson.JsonObject
import android.widget.TableRow
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walletapplicatiom.appRecycler.TransactionsRecyclerAdapter


class TransactionStatement : AppCompatActivity() {
    private lateinit var binding: ActivityTransactioStatementBinding
    private lateinit var viewmodel:TransactionViewModel
    lateinit var shared: SharedPreferences
    private var adapter:RecyclerView.Adapter<TransactionsRecyclerAdapter.ViewHolder>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactioStatementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        shared = getSharedPreferences("prefs", Context.MODE_PRIVATE)


        getTransactions()
    }

    private fun getTransactions() {
        val repo = TransactionRepository()
        val viewModelFactory = TransactionViewModelFactory(repo)
        viewmodel = ViewModelProvider(this,viewModelFactory).get(TransactionViewModel::class.java)


        var json =  JsonObject()
        json.addProperty("customerId",shared.getString("customerId",null))

        viewmodel.getTransactions(json)
        viewmodel.transactionsList.observe(this,{transaction->
            adapter = transaction.body()?.let { TransactionsRecyclerAdapter(it) }
            binding.recycler.layoutManager = LinearLayoutManager(this)
            binding.recycler.adapter = adapter

        })
    }
}