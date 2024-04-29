package com.technoPia.primersAuxilis.interfaces

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.technoPia.primersAuxilis.R
import android.widget.Button
import android.widget.TextView
import com.technoPia.primersAuxilis.room.App
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class IntentPerfil : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfil)

        val prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE)
        val savedName = prefs.getString("username", null)
        val savedMail = prefs.getString("email", null)

        val tvUsername=findViewById<TextView>(R.id.tvUsername)
        tvUsername.text=savedName

        val btnAprenentatge= findViewById<Button>(R.id.btn_apt)
        btnAprenentatge.setOnClickListener {
            val i = Intent(this, IntentAprenentatge::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(i)
            finish()
        }

        val btnCapitols= findViewById<Button>(R.id.btn_cap)
        btnCapitols.setOnClickListener {
            val i = Intent(this, IntentCapitols::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(i)
            finish()
        }

        val btnMj= findViewById<Button>(R.id.btn_mj)
        btnMj.setOnClickListener {
            val i = Intent(this, IntentMinijocs::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(i)
            finish()
        }

        val btnLo=findViewById<Button>(R.id.btnLo)
        btnLo.setOnClickListener {
            val message = "Estas segur de tancar sessió?"
            MaterialAlertDialogBuilder(this)
                .setMessage(message)
                .setPositiveButton("OK") { _, _ ->
                    val editor = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit()
                    editor.clear()
                    editor.apply()

                    val i = Intent(this, MainActivity::class.java)
                    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(i)
                    finish()
                }
                .setNegativeButton("Cancel") { _, _ ->
                    //
                }
                .show()
        }

        val btnReset=findViewById<Button>(R.id.btnReset)
        btnReset.setOnClickListener {
            val message = "Estas segur de voler reiniciar el teu progress?"
            MaterialAlertDialogBuilder(this)
                .setMessage(message)
                .setPositiveButton("OK") { _, _ ->
                    GlobalScope.launch {
                        if (savedMail != null) {
                            App.db.UserDao().levelUpdate(savedMail,1)
                        }
                    }
                    val editor = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit()
                    editor.clear()
                    editor.apply()

                    val i = Intent(this, MainActivity::class.java)
                    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(i)
                    finish()
                }
                .setNegativeButton("Cancel") { _, _ ->
                    //
                }
                .show()
        }
    }
}
