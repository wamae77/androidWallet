package com.example.walletapplicatiom.database.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walletapplicatiom.database.repository.DbTransRepository
import com.example.walletapplicatiom.models.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DbTransViewModel(private val dbTransRepository: DbTransRepository):ViewModel() {
    val trans: MutableLiveData<List<Transaction>> = MutableLiveData()

    fun getUserVM(context: Context){
        viewModelScope.launch {
            val u = dbTransRepository.getTransactionsRepo(context)
            trans.value = u
        }
    }

    fun addTransactionVm(context: Context,transaction: Transaction){
                viewModelScope.launch(Dispatchers.IO) {
                    dbTransRepository.addTransaction(context,transaction)
                }
    }

}