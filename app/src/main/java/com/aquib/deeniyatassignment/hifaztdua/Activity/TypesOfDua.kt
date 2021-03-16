package com.aquib.deeniyatassignment.hifaztdua.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aquib.deeniyatassignment.hifaztdua.R
import kotlinx.android.synthetic.main.activity_types_of_dua.*

class TypesOfDua : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_types_of_dua)

        typeOne.setOnClickListener(){
            startActivity(Intent(applicationContext,MainActivity::class.java).putExtra("type",1))
        }
        typeTwo.setOnClickListener(){
            startActivity(Intent(applicationContext,MainActivity::class.java).putExtra("type",2))
        }
    }
}