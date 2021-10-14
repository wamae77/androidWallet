package com.example.walletapplicatiom.appRecycler

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.walletapplicatiom.R
import com.example.walletapplicatiom.models.Transaction

class TransactionsRecyclerAdapter(private val transactionList: List<Transaction>) :RecyclerView.Adapter<TransactionsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionsRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.table_item,parent,false
        )
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: TransactionsRecyclerAdapter.ViewHolder, position: Int) {
        Log.d( "onBindViewHolder: ",transactionList.toString())
        val currentItem = transactionList[position]
        holder.transId.text = currentItem.accountNo
        holder.amount.text = currentItem.amount
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var transId:TextView = itemView.findViewById(R.id.ctId)
        var amount:TextView = itemView.findViewById(R.id.ctA)

    }
}