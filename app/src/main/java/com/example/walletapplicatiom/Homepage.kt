package com.example.walletapplicatiom

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.walletapplicatiom.api.repository.CustomerRepository
import com.example.walletapplicatiom.api.viewmodels.MainActivityViewModel
import com.example.walletapplicatiom.api.viewmodels.MainViewModelFactory
import com.example.walletapplicatiom.database.repository.DbCustomerRepository
import com.example.walletapplicatiom.database.viewmodel.DbCustomerViewModelFactory
import com.example.walletapplicatiom.database.viewmodel.DbCustomerViewmodel
import com.example.walletapplicatiom.databinding.ActivityHomepageBinding
import com.example.walletapplicatiom.models.Account

class Homepage : AppCompatActivity() {
    private lateinit var binding: ActivityHomepageBinding
    private lateinit var dbCustomerViewmodel: DbCustomerViewmodel
    private lateinit var viewmodel:MainActivityViewModel

    lateinit var shared: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        shared = getSharedPreferences("prefs", Context.MODE_PRIVATE)

        queryCustomerName()


        binding.cardSendMoney.setOnClickListener{moveToSendMoney()}
        binding.cardViewStatement.setOnClickListener { moveToStatement() }
        binding.cardLogout.setOnClickListener{
            logout()
        }
        binding.cardBalance.setOnClickListener {
            getBalance()
        }
        binding.cardProfile.setOnClickListener {
            moveToProfile()
        }
        binding.cardViewStatement.setOnClickListener {
            moveToTransStatement()
        }
        binding.cardLastTransaction.setOnClickListener {
            val intent = Intent(this, LatestTransactions::class.java)
            startActivity(intent)
        }


    }



    private fun getBalance(){

        val repository = CustomerRepository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewmodel = ViewModelProvider(this,viewModelFactory).get(MainActivityViewModel::class.java)

        val cid = shared.getString("customerId",null) ?: return
        val account = Account(id = 0, accountNo = "", customerId = cid, balance = 0.0)

        viewmodel.accountBalanceVm(account)
        viewmodel.balance.observe(this, { response->
            if (response.isSuccessful){

               val acc= response.body()
                if (acc != null) {
                    acc.customerId
                    openAlertDialog(acc)

                }
            }else{
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun openAlertDialog(account: Account) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Balance")
        builder.setMessage(account.balance.toString())
        builder.setPositiveButton("ok",null)
        val dialog :AlertDialog=builder.create()
        dialog.show()
    }

    private fun logout() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun queryCustomerName() {
        val repo = DbCustomerRepository()
        val vmf = DbCustomerViewModelFactory(repo)
        dbCustomerViewmodel = ViewModelProvider(this,vmf).get(DbCustomerViewmodel::class.java)

        val cid = shared.getString("customerId",null) ?: return


        dbCustomerViewmodel.getUserVM(this, cid)
        dbCustomerViewmodel.cust.observe(this,{customer ->
            binding.textView.text = "Welcome ${customer.firstName.replace("\"", "")}"
        })
    }

    private fun moveToStatement() {
        val intent = Intent(this, TransactionStatement::class.java)
        startActivity(intent)
    }

    private fun moveToSendMoney() {
        val intent = Intent(this, SendMoney::class.java)
        startActivity(intent)
    }

    private fun moveToProfile() {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }
    private fun moveToTransStatement() {
        val intent = Intent(this, TransactionStatement::class.java)
        startActivity(intent)
    }

}