package com.example.walletapplicatiom.dialogs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.example.walletapplicatiom.Homepage
import com.example.walletapplicatiom.MainActivity
import com.example.walletapplicatiom.R

class CustomDialogSuccess:DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootview =  inflater.inflate(R.layout.custom_dialog_success,container,false)
        val im = rootview.findViewById<ImageView>(R.id.closer)

        im.setOnClickListener{
            dismiss()
            val intent = Intent(rootview.context, Homepage::class.java)
            startActivity(intent)
        }
        return rootview
    }
}