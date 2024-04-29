package com.technoPia.primersAuxilis.interfaces

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.technoPia.primersAuxilis.R

class IntentHome: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE)
        val savedName = prefs.getString("username", null)

        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        tvUsername.text= "Benvolgut $savedName"

        val btnAprenentatge= findViewById<Button>(R.id.btn_apt)
        btnAprenentatge.setOnClickListener {
            val i = Intent(this, IntentAprenentatge::class.java)
            startActivity(i)
        }

        val btnCapitols= findViewById<Button>(R.id.btn_cap)
        btnCapitols.setOnClickListener {
            val i = Intent(this, IntentCapitols::class.java)
            startActivity(i)
        }

        val btnMj= findViewById<Button>(R.id.btn_mj)
        btnMj.setOnClickListener {
            val i = Intent(this, IntentMinijocs::class.java)
            startActivity(i)
        }

        val btnPerfil= findViewById<Button>(R.id.btn_per)
        btnPerfil.setOnClickListener {
            val i = Intent(this, IntentPerfil::class.java)
            startActivity(i)
        }
    }
}