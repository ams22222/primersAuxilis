package com.technoPia.primersAuxilis.interfaces

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.technoPia.primersAuxilis.R
import android.widget.Button


class IntentMinijocs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.minijocs);

        val btnAprenentatge= findViewById<Button>(R.id.btn_apt)
        btnAprenentatge.setOnClickListener {
            val i = Intent(this, IntentAprenentatge::class.java)
            startActivity(i)
        }

        val btnCapitols= findViewById<Button>(R.id.btn_cap)
        btnCapitols.setOnClickListener {
            val i = Intent(this, IntentCapitols::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i)
            finish();
        }

        val btnPerfil= findViewById<Button>(R.id.btn_per)
        btnPerfil.setOnClickListener {
            val i = Intent(this, IntentPerfil::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i)
            finish();
        }

        val btnRitme= findViewById<Button>(R.id.btn_ritme)
        btnRitme.setOnClickListener {
            val i = Intent(this, IntentRitme::class.java)
            startActivity(i)
        }
    }
}
