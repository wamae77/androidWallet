package com.example.walletapplicatiom.appRecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.walletapplicatiom.R
import com.example.walletapplicatiom.models.Transaction

class LatestTransactionsRAdapter(private val transactionList: List<Transaction>) : RecyclerView.Adapter<LatestTransactionsRAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LatestTransactionsRAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.table_item,parent,false
        )
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: LatestTransactionsRAdapter.ViewHolder, position: Int) {
        val currentItem = transactionList[position]

            holder.transId.text = currentItem.id.toString()
            holder.amount.text = currentItem.amount

    }

    override fun getItemCount(): Int {
        return transactionList.size
    }


    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var transId: TextView = itemView.findViewById(R.id.ctId)
        var amount: TextView = itemView.findViewById(R.id.ctA)

    }
}