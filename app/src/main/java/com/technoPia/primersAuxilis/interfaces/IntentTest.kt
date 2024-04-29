package com.technoPia.primersAuxilis.interfaces

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.technoPia.primersAuxilis.R
import com.technoPia.primersAuxilis.room.App
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class IntentTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preguntes);

        val prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE)
        val savedLvl = prefs.getString("level", null)
        val savedMail = prefs.getString("email", null)

        val btnCancelar= findViewById<Button>(R.id.btn_cancel)
        btnCancelar.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        val btnValidar= findViewById<Button>(R.id.btn_val)
        btnValidar.setOnClickListener{
            val Answer=findViewById<EditText>(R.id.edt_ans)

            if(Answer.text.toString()=="112"){
                val tvAns=findViewById<TextView>(R.id.tv_res)

                val correcte=SpannableStringBuilder("CORRECTE")
                tvAns.text=correcte
                tvAns.visibility = View.VISIBLE;

                val points=SpannableStringBuilder("+10")
                val tvpunts=findViewById<TextView>(R.id.tv_points)
                tvpunts.text=points

                if (savedLvl != null) {
                    if(savedLvl.toInt()<2) {
                        GlobalScope.launch {
                            App.db.UserDao().levelUpdate(savedMail.toString(),2)
                            val editor = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit()
                            editor.putString("level", 2.toString())
                            editor.apply()
                        }
                    }
                }
                val returnIntent = Intent()
                setResult(RESULT_OK, returnIntent)
                esperarYCerrar(5000);
            }
            else{
                val tvAns=findViewById<TextView>(R.id.tv_res)

                val incorrecte=SpannableStringBuilder("INCORRECTE")
                tvAns.text=incorrecte
                tvAns.visibility = View.VISIBLE;
            }
        }
    }

    fun esperarYCerrar(milisegundos: Int) {
        val handler = Handler()
        handler.postDelayed(Runnable { // acciones que se ejecutan tras los milisegundos
            finish()
        }, milisegundos.toLong())
    }
}
