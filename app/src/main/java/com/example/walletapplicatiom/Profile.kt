package com.example.walletapplicatiom

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.walletapplicatiom.database.repository.DbCustomerRepository
import com.example.walletapplicatiom.database.viewmodel.DbCustomerViewModelFactory
import com.example.walletapplicatiom.database.viewmodel.DbCustomerViewmodel
import com.example.walletapplicatiom.databinding.ActivityHomepageBinding
import com.example.walletapplicatiom.databinding.ActivityProfileBinding

class Profile : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var dbCustomerViewmodel: DbCustomerViewmodel
    lateinit var shared: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        shared = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        queryUser()
        binding.button.setOnClickListener{
            val intent = Intent(this, Homepage::class.java)
            startActivity(intent)
        }
        Log.d(TAG, "onCreate: hello")


    }

    private fun queryUser() {
        val repo = DbCustomerRepository()
        val vmf = DbCustomerViewModelFactory(repo)
        dbCustomerViewmodel = ViewModelProvider(this,vmf).get(DbCustomerViewmodel::class.java)

        val cid = shared.getString("customerId",null) ?: return
        val accountNo = shared.getString("accountNo",null) ?: return

        Log.d( "queryCustomerName: ",accountNo)

        dbCustomerViewmodel.getUserVM(this, cid)
        dbCustomerViewmodel.cust.observe(this,{customer ->
            Log.d( "queryCustomerName: ",accountNo)
            binding.name.text = "Name: ${customer.firstName.replace("\"", "")}"
            binding.custId.text = "CustomerId: ${customer.customerId.replace("\"", "")}"
            binding.email.text = "Email: ${customer.email.replace("\"", "")}"
            binding.custAcc.text = accountNo
        })
    }

}