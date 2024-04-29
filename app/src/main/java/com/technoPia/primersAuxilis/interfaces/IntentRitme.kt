package com.technoPia.primersAuxilis.interfaces

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.technoPia.primersAuxilis.R

class IntentRitme : AppCompatActivity() {
    var clics: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ritme)

        val ivCor = findViewById<ImageView>(R.id.ivCor)
        val initialScale = ivCor.scaleX
        val scaleFactor = 1.2f // adjust this value to change the zoom level
        val animationDuration = 200L // adjust this value to change the animation duration in milliseconds

        val countDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                val textView: TextView = findViewById(R.id.tvCont)

                if (secondsLeft > 45) {
                    textView.text = secondsLeft.toString()
                } else {
                    textView.text = ""
                }

                ivCor.setOnClickListener {
                    if (ivCor.scaleX >= 1.5f) { // check if the current scale is already 2 or greater
                        return@setOnClickListener
                    }

                    clics++
                    val currentScale = ivCor.scaleX
                    ivCor.animate()
                        .scaleX(currentScale * scaleFactor)
                        .scaleY(currentScale * scaleFactor)
                        .setDuration(animationDuration)
                        .withEndAction {
                            ivCor.animate()
                                .scaleX(initialScale)
                                .scaleY(initialScale)
                                .setDuration(animationDuration)
                                .start()
                        }
                        .start()
                }
            }

            override fun onFinish() {
                ivCor.isClickable = false

                val textView: TextView = findViewById(R.id.tvCont)
                textView.text = "TEMPS!"

                val tvPuntu = findViewById<TextView>(R.id.tvPuntu)
                tvPuntu.text = "Has fet $clics compresions"

                val tvInfo = findViewById<TextView>(R.id.tvInfo)
                tvInfo.text = "S'ha acabat el temps, si has aconseguit entre 100 i 120 clics en el minut, has assolit el repte"
            }
        }

        val btnComencem = findViewById<Button>(R.id.btnComencem)
        btnComencem.setOnClickListener {
            btnComencem.visibility = View.INVISIBLE

            countDownTimer.start()
        }

        val btnTornar = findViewById<Button>(R.id.btnTornar)
        btnTornar.setOnClickListener {
            finish()
        }
    }
}