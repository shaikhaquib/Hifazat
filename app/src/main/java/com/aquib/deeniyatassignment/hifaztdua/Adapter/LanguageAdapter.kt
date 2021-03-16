package com.aquib.deeniyatassignment.hifaztdua.Adapter

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aquib.deeniyatassignment.hifaztdua.Activity.MainActivity
import com.aquib.deeniyatassignment.hifaztdua.Activity.SplashActivity
import com.aquib.deeniyatassignment.hifaztdua.Models.LanguageList
import com.aquib.deeniyatassignment.hifaztdua.R
import kotlinx.android.synthetic.main.language_item.view.*

class LanguageAdapter(val context: Context, val dialog: Dialog,val type: Int) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.language_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val languageList: LanguageList = SplashActivity.languageList[position]

        holder.displayname.setText(languageList.displayName)
        holder.icon.setTextColor(Color.parseColor(languageList.backgroundColor))
        holder.icon.setText(languageList.displayName!!.substring(0, 2))
        holder.cardView.setCardBackgroundColor(Color.parseColor(languageList.backgroundColor))

        holder.cardView.setOnClickListener() {
            val sharedPreference = context.getSharedPreferences("Language", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.putString(SplashActivity.LANGUAGE_CODE, languageList.langeCode)
            editor.apply()
            dialog.dismiss()
            context.startActivity(Intent(context, MainActivity::class.java).putExtra("type",type))
            val activity = context as MainActivity
            activity.finish()
        }


    }

    override fun getItemCount(): Int {
        return SplashActivity.languageList.size
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cardView = itemView.card
    val icon = itemView.txtIcon
    val displayname = itemView.txtDisplayName
}