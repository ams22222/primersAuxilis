package com.technoPia.primersAuxilis.interfaces

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.technoPia.primersAuxilis.R
import android.widget.Button
import android.widget.MediaController
import android.widget.TextView
import com.technoPia.primersAuxilis.classes.Teoria
import com.technoPia.primersAuxilis.classes.Teorias
import com.google.gson.Gson


class IntentAprenentatge : AppCompatActivity() {
    private lateinit var teorias: Teorias
    private lateinit var teoria: Teoria
    var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aprenentatge);

        val json = application.assets.open("teorias.json").bufferedReader().use { it.readText() }

        val gson = Gson()
        teorias = gson.fromJson(json, Teorias::class.java)

        if (savedInstanceState != null) {
            id = savedInstanceState.getInt("id")
        }
        val teoria = id?.let { searchById(it) }

        if (teoria != null) {
            val titol =  findViewById<TextView>(R.id.tvTitol)
            titol.text=teoria.title

          val texto =  findViewById<TextView>(R.id.tvTeoria)
          texto.text=teoria.text

            val url = teoria.url
            val resourceId = resources.getIdentifier(url.substringAfter("@raw/"), "raw", packageName)

            val vv = findViewById<VideoView>(R.id.vview)
            val uri = Uri.parse("android.resource://${packageName}/${resourceId}")
            vv.setVideoURI(uri)
            val mediaController = MediaController(this)
            mediaController.setAnchorView(vv)
            mediaController.setMediaPlayer(vv)
            vv.setMediaController(mediaController)
        }

        val btnObs = findViewById<Button>(R.id.btnObs)
        btnObs.setOnClickListener {
            if(id!=1) {
                id = 1
                recreate()
            }
        }
        val btnEmergen = findViewById<Button>(R.id.btnEmergen)
        btnEmergen.setOnClickListener {
            if(id!=0) {
                id = 0
                recreate()
            }
        }
        val btnRCP = findViewById<Button>(R.id.btnRCP)
        btnRCP.setOnClickListener {
            if(id!=2) {
                id = 2
                recreate()
            }
        }
        val btnPos = findViewById<Button>(R.id.btnPos)
        btnPos.setOnClickListener {
            if(id!=3) {
                id = 3
                recreate()
            }
        }
        val btnCapitols= findViewById<Button>(R.id.btn_cap)
        btnCapitols.setOnClickListener {
            val i = Intent(this, IntentCapitols::class.java)
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
    }

    fun searchById(id: Int): Teoria? {
        for (teoria in teorias.teorias) {
            if (teoria.id == id) {
                this.teoria =teoria
                return teoria
            }
        }
        return null // ID not found
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("id", id)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        id = savedInstanceState.getInt("id")
    }
}
