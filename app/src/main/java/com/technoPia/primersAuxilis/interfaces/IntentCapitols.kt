package com.technoPia.primersAuxilis.interfaces

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.technoPia.primersAuxilis.R
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar


class IntentCapitols : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.capitols);

        val prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE)
        val savedLvl = prefs.getString("level", null)



            if(savedLvl.toString().toInt()>1){
                val checkBox = findViewById<CheckBox>(R.id.cb_questionari1)
                checkBox.setChecked(true);

               val ivCap2 = findViewById<ImageView>(R.id.ivCap2)
                ivCap2.visibility = View.INVISIBLE

                val cbQuestionari2 = findViewById<CheckBox>(R.id.cb_questionari2)
                cbQuestionari2.visibility = View.VISIBLE

                val btnTest2 = findViewById<Button>(R.id.btn_test2)
                btnTest2.visibility = View.VISIBLE

                var resultsLauncher2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
                { result ->
                    if (result.resultCode == Activity.RESULT_OK) {

                        val checkBox2 = findViewById<CheckBox>(R.id.cb_questionari2)
                        checkBox2.setChecked(true);
                        recreate()

                    } else {
                        Snackbar.make(
                            findViewById(android.R.id.content),
                            "El qüestionari ha estat cancelat!",
                            Snackbar.LENGTH_LONG
                        )
                            .show()
                    }
                }
                btnTest2.setOnClickListener {
                    val i = Intent(this, IntentTest2::class.java)
                    resultsLauncher2.launch(i)
                }
            }

        if(savedLvl.toString().toInt()>2){
            val checkBox = findViewById<CheckBox>(R.id.cb_questionari1)
            checkBox.setChecked(true);

            val checkBox2 = findViewById<CheckBox>(R.id.cb_questionari2)
            checkBox2.setChecked(true);

            val ivCap3 = findViewById<ImageView>(R.id.ivCap3)
            ivCap3.visibility = View.INVISIBLE

            val cbQuestionari3 = findViewById<CheckBox>(R.id.cb_questionari3)
            cbQuestionari3.visibility = View.VISIBLE

            val btnTest3 = findViewById<Button>(R.id.btn_test3)
            btnTest3.visibility = View.VISIBLE


            btnTest3.setOnClickListener {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Proximament!",
                    Snackbar.LENGTH_LONG
                )
                    .show()
            }
        }

        val btnAprenentatge= findViewById<Button>(R.id.btn_apt)
        btnAprenentatge.setOnClickListener {
            val i = Intent(this, IntentAprenentatge::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i)
            finish();
        }

        val btnMj= findViewById<Button>(R.id.btn_mj)
        btnMj.setOnClickListener {
            val i = Intent(this, IntentMinijocs::class.java)
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

        var resultsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val checkBox = findViewById<CheckBox>(R.id.cb_questionari1)
                checkBox.setChecked(true);
                recreate()

            } else {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "El qüestionari ha estat cancelat!",
                    Snackbar.LENGTH_LONG
                )
                    .show()
            }
        }

        val btnTest= findViewById<Button>(R.id.btn_test)
        btnTest.setOnClickListener {
            val i = Intent(this, IntentTest::class.java)
            resultsLauncher.launch(i)
        }

    }
}
