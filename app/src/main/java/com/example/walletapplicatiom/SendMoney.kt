package com.example.walletapplicatiom

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.walletapplicatiom.api.repository.TransactionRepository
import com.example.walletapplicatiom.api.viewmodels.TransactionViewModel
import com.example.walletapplicatiom.api.viewmodels.TransactionViewModelFactory
import com.example.walletapplicatiom.database.repository.DbTransRepository
import com.example.walletapplicatiom.database.viewmodel.DbTransViewModel
import com.example.walletapplicatiom.database.viewmodel.DbTransVmFactory
import com.example.walletapplicatiom.databinding.ActivitySendMoneyBinding
import com.example.walletapplicatiom.databinding.ActivityTransactioStatementBinding
import com.example.walletapplicatiom.databinding.CustomDialogBinding
import com.example.walletapplicatiom.dialogs.CustomDialogSuccess
import com.example.walletapplicatiom.models.Account
import com.example.walletapplicatiom.models.Transaction
import com.google.gson.Gson
import com.google.gson.JsonObject

class SendMoney : AppCompatActivity() {
    private lateinit var binding: ActivitySendMoneyBinding
    private lateinit var viewmodel:TransactionViewModel
    lateinit var shared: SharedPreferences
    lateinit var dbTransViewModel: DbTransViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        shared = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        //getinput
        //storeInputindb
        //send to endpoint
        binding.buttonSend.setOnClickListener{
            sendMoney()
        }


    }

    private fun insertRecordIntoDb(accountTo:String,amount :String){

        val transaction = Transaction(id = 0,accountNo= accountTo,amount=amount)
        val repo = DbTransRepository()
        val factory = DbTransVmFactory(repo)
        dbTransViewModel = ViewModelProvider(this,factory).get(DbTransViewModel::class.java)
        dbTransViewModel.addTransactionVm(this,transaction)
    }

    private fun sendMoney() {
        var account = binding.AccountToEditText.text
        var amount = binding.amountEditText.text

        insertRecordIntoDb(accountTo = account.toString().uppercase(),amount = amount.toString())

        var json =  JsonObject()
        json.addProperty("customerId",shared.getString("customerId",null))
        json.addProperty("accountFrom",shared.getString("accountNo",null))
        json.addProperty("accountTo",account.toString())
        json.addProperty("amount",amount.toString())

        val repo = TransactionRepository()
        val viewModelFactory = TransactionViewModelFactory(repo)
        viewmodel = ViewModelProvider(this,viewModelFactory).get(TransactionViewModel::class.java)

        viewmodel.sendMoneyVm(json)
        viewmodel.response.observe(this,{response->

            if (!response.isSuccessful){
                Toast.makeText(this, "Transaction failed", Toast.LENGTH_SHORT).show()

            }else {
                val dialog = CustomDialogSuccess()
                dialog.show(supportFragmentManager, "dialog")
            }
        })
    }

}