package com.example.walletapplicatiom

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.walletapplicatiom.databinding.ActivityMainBinding
import com.example.walletapplicatiom.models.Login
import com.example.walletapplicatiom.api.repository.CustomerRepository
import com.example.walletapplicatiom.api.viewmodels.MainActivityViewModel
import com.example.walletapplicatiom.api.viewmodels.MainViewModelFactory
import com.example.walletapplicatiom.database.repository.DbCustomerRepository
import com.example.walletapplicatiom.database.viewmodel.DbCustomerViewModelFactory
import com.example.walletapplicatiom.database.viewmodel.DbCustomerViewmodel
import com.example.walletapplicatiom.models.Account
import com.example.walletapplicatiom.models.Customer
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewmodel:MainActivityViewModel
    private lateinit var dbCustomerViewmodel: DbCustomerViewmodel
    lateinit var shared: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        shared = getSharedPreferences("prefs", Context.MODE_PRIVATE)


        binding.buttonLogin.setOnClickListener{
            sendInputToServer()
        }



    }

    private fun sendInputToServer() {
        val loginInput: Login = getUserInput()

        val repository = CustomerRepository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewmodel = ViewModelProvider(this,viewModelFactory).get(MainActivityViewModel::class.java)

        viewmodel.loginCustomerVM(loginInput)
        viewmodel.response.observe(this, { response->
            if (response.isSuccessful){
                if (response.body() !=null) {

                    val json = response.body()!!["customerAccount"]
                    val acc = Gson().fromJson(json,Account::class.java)
                    val edit = shared.edit()
                    edit.putString("customerId",acc.customerId)
                    edit.putString("accountNo",acc.accountNo)
                    edit.apply()

                    val custId = response.body()!!["customerId"].toString()
                    val email = response.body()!!["email"].toString()
                    val firsn =response.body()!!["firstName"].toString()
                    val lastN=response.body()!!["lastName"].toString()
                    val pin = response.body()!!["pin"].toString()
                    val customer = Customer(id = 0,customerId =custId,firstName = firsn,lastName = lastN,pin = pin,email = email)

                    insertCustomerIntoDb(customer)
                    moveToHomepage()
                }
            }else{
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun insertCustomerIntoDb(body: Customer?) {
        val repo = DbCustomerRepository()
        val vmf = DbCustomerViewModelFactory(repo)
        dbCustomerViewmodel = ViewModelProvider(this,vmf).get(DbCustomerViewmodel::class.java)
        if (body != null) {
            dbCustomerViewmodel.addCustomerVM(this,body)
        }
    }

    private fun getUserInput() :Login{
        val userId = binding.customerIdEditText.text
        val pin = binding.customerPinEditText.text
        return Login(customerId = userId.toString().uppercase(), pin = pin.toString())

    }


    private fun moveToHomepage(){
        val intent = Intent(this, Homepage::class.java)
        startActivity(intent)
    }
}