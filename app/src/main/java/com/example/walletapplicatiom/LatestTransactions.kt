package com.example.walletapplicatiom

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walletapplicatiom.api.repository.TransactionRepository
import com.example.walletapplicatiom.api.viewmodels.TransactionViewModel
import com.example.walletapplicatiom.api.viewmodels.TransactionViewModelFactory
import com.example.walletapplicatiom.appRecycler.LatestTransactionsRAdapter
import com.example.walletapplicatiom.appRecycler.TransactionsRecyclerAdapter
import com.example.walletapplicatiom.database.repository.DbTransRepository
import com.example.walletapplicatiom.database.viewmodel.DbTransViewModel
import com.example.walletapplicatiom.database.viewmodel.DbTransVmFactory
import com.example.walletapplicatiom.databinding.ActivityLatestTransactionsBinding
import com.example.walletapplicatiom.models.Transaction
import com.google.gson.JsonObject


class LatestTransactions : AppCompatActivity() {
    private lateinit var binding: ActivityLatestTransactionsBinding
    lateinit var shared: SharedPreferences
    private var adapter: RecyclerView.Adapter<LatestTransactionsRAdapter.ViewHolder>?=null
    lateinit var dbTransViewModel: DbTransViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLatestTransactionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        shared = getSharedPreferences("prefs", Context.MODE_PRIVATE)

        getTransactionsfromDb()
    }

    private fun getTransactionsfromDb() {

        val repo = DbTransRepository()
        val factory = DbTransVmFactory(repo)
        dbTransViewModel = ViewModelProvider(this,factory).get(DbTransViewModel::class.java)
        dbTransViewModel.getUserVM(context = this)
        dbTransViewModel.trans.observe(this,{list->
            adapter =   LatestTransactionsRAdapter(list)
            binding.recycler.layoutManager = LinearLayoutManager(this)
            binding.recycler.adapter = adapter
        })


    }
}