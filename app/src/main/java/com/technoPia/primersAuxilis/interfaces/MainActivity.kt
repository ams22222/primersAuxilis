package com.technoPia.primersAuxilis.interfaces

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.room.Room
import com.technoPia.primersAuxilis.R
import com.technoPia.primersAuxilis.room.DataBase
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var db: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val registre= findViewById<TextView>(R.id.tvRegistre)

        registre.setOnClickListener{
            val i = Intent(this, IntentRegister::class.java)
            resultsLauncher.launch(i)
        }

        db = Room.databaseBuilder(
            applicationContext,
            DataBase::class.java, "room-db"
        ).build()


        val cbRemember = findViewById<CheckBox>(R.id.cbRemember)

        var log = findViewById<Button>(R.id.btnLogin)
        log.setOnClickListener {
            val email = findViewById<EditText>(R.id.edtEmailL).text.toString()
            val password = findViewById<EditText>(R.id.edtPasswordL).text.toString()

            var context = this
            if (email.isNotEmpty() && password.isNotEmpty()) {
                GlobalScope.launch {
                    val user = db.UserDao().getUser(email, password)
                    runOnUiThread {
                        if (user != null) {
                            Toast.makeText(
                                applicationContext,
                                "Login successful",
                                Toast.LENGTH_SHORT
                            ).show()
                            // Redirect to the next activity
                            val editor = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit()
                            editor.putString("email", email)
                            editor.putString("password", password)
                            editor.putString("username", user.username)
                            editor.putString("level", user.level.toString())
                            editor.apply()

                            val i = Intent(context, IntentHome::class.java)
                            startActivity(i)
                            finish();
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Invalid username or password",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "Please enter username and password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    private var resultsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            Snackbar.make(
                findViewById(R.id.tvRegistre),
                "Usuari creat correctament!",
                BaseTransientBottomBar.LENGTH_LONG
            ).show()
        } else {
            Snackbar.make(
                findViewById(android.R.id.content),
                "La operació ha sigut cancel·lada!",
                Snackbar.LENGTH_LONG
            )
                .show()
        }
    }
}