package com.technoPia.primersAuxilis.interfaces

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.technoPia.primersAuxilis.R
import com.technoPia.primersAuxilis.room.App
import com.technoPia.primersAuxilis.room.User
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class IntentRegister: AppCompatActivity() {

    private lateinit var edat: EditText
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var email: EditText
    private lateinit var buttonreg: Button

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        edat = findViewById(R.id.edtEdat)
        username = findViewById(R.id.edtUsername)
        password = findViewById(R.id.edtPassword)
        email = findViewById(R.id.edtEmail)
        buttonreg = findViewById(R.id.btnRegistre)


        buttonreg.setOnClickListener{
            try{
                if(email.text.toString().isEmpty() || username.text.toString().isEmpty() || password.text.toString().isEmpty() || edat.text.toString().isEmpty()){
                    Snackbar.make(
                        findViewById(R.id.btnRegistre),
                        "S'han d'omplir tots els camps, són obligatoris!",
                        BaseTransientBottomBar.LENGTH_LONG
                    ).show()
                    throw EmptyStackException()
                }


                if(edat.text.toString().toInt()<18){
                    Snackbar.make(
                        findViewById(R.id.btnRegistre),
                        "Heu de ser major d'edat!",
                        BaseTransientBottomBar.LENGTH_LONG
                    ).show()
                    throw EmptyStackException()
                }
                if(password.text.toString().length<8){
                    Snackbar.make(
                        findViewById(R.id.btnRegistre),
                        "La contrasenya ha de contenir com a mínim 8 caràcters!",
                        BaseTransientBottomBar.LENGTH_LONG
                    ).show()
                    throw EmptyStackException()
                }

                if (!isValidEmail(email.text.toString())) {
                    Snackbar.make(
                        findViewById(R.id.btnRegistre),
                        "Introdueix un mail vàlid",
                        BaseTransientBottomBar.LENGTH_LONG
                    ).show()
                    throw EmptyStackException()                }

                GlobalScope.launch {
                    if (!App.db.UserDao().codecheck(email.text.toString())) {
                        val level=1
                        val user= User(email.text.toString(), username.text.toString(),password.text.toString(),level)
                        App.db.UserDao().insert(user)

                        setResult(RESULT_OK,intent)
                        finish()
                    }
                    else {
                        Snackbar.make(
                            findViewById(R.id.btnRegistre),
                            "Aquest mail ja existeix!",
                            BaseTransientBottomBar.LENGTH_LONG
                        ).show()
                    }
                }
            }catch  (_: Exception){}
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
        return emailRegex.matches(email)
    }
}