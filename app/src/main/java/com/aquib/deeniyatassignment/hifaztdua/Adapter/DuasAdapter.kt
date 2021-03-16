package com.aquib.deeniyatassignment.hifaztdua.Adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aquib.deeniyatassignment.hifaztdua.Activity.BookOnboardingActivity
import com.aquib.deeniyatassignment.hifaztdua.Activity.SplashActivity
import com.aquib.deeniyatassignment.hifaztdua.Models.DuaModel
import com.aquib.deeniyatassignment.hifaztdua.Models.LanguageList
import com.aquib.deeniyatassignment.hifaztdua.R
import kotlinx.android.synthetic.main.list_item_urdu.view.*

var selectedPosition : Int = -1

class DuasAdapterr(val context: Context,  val type: Int) :
    RecyclerView.Adapter<DuasAdapterr.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val sharedPreference = context.getSharedPreferences("Language", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        val x = sharedPreference.getString(SplashActivity.LANGUAGE_CODE,"en");
        if (x.equals("ur"))
            return ViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.list_item_urdu,
                    parent,
                    false
                ))
            else
                return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item,
                parent,
                false
            ))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var duaList : DuaModel? = null;

        if (type == 1)
            duaList = SplashActivity.duasforBimari[position]
        else
            duaList = SplashActivity.duasforHifaazat[position]

        if (selectedPosition == position){
            holder.itemView.setBackgroundColor(Color.parseColor("#608BC34A"))
        }else{
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }


        val sharedPreference = context.getSharedPreferences("Language", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        val x = sharedPreference.getString(SplashActivity.LANGUAGE_CODE,"en");
        when(x) {
            "hi" ->
                holder.displayname.setText(duaList.hindiTitle)
            "gu" ->
                holder.displayname.setText(duaList.gujratiTitle)
            "en" ->
                holder.displayname.setText(duaList.transTitle)
            "ur" ->
                holder.displayname.setText(duaList.urduTitle)
        }

        holder.itemView.setOnClickListener {
                selectedPosition = position
                notifyDataSetChanged()
            context.startActivity(Intent(context,BookOnboardingActivity::class.java).putExtra("position",position).putExtra("type",type))
        }


    }

    override fun getItemCount(): Int {
        if (type == 1)
            return SplashActivity.duasforBimari.size
        else
            return SplashActivity.duasforHifaazat.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val displayname = itemView.title
    }
}

