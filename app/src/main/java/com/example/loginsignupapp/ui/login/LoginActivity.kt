package com.example.loginsignupapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.loginsignupapp.DBHelper
import com.example.loginsignupapp.HomeActivity
import com.example.loginsignupapp.R
import com.example.loginsignupapp.data.FingerPrintScanner
class LoginActivity: AppCompatActivity() {
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var userEmail: EditText
    lateinit var btnlogin: Button
    lateinit var FPScanner: Button
    lateinit var DB: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById<EditText>(R.id.SigninUsername)
        password = findViewById<EditText>(R.id.SigninPassword)
        userEmail = findViewById<EditText>(R.id.SigninEmail)
        btnlogin = findViewById<Button>(R.id.signinBtn)
        FPScanner = findViewById<Button>(R.id.FPScanner)
        DB = DBHelper(this)

        btnlogin.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()
            val userEmail = userEmail.text.toString()

            if (user.isEmpty() || pass.isEmpty()) {
                notifyUser("Please enter a username, email and password")
            }
            else{
                val checkuserpass = DB.checkUsernamePassword(user, pass, userEmail)
                if (checkuserpass) {
                    notifyUser("Sign in successful!")
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
                else {
                    notifyUser("Incorrect login details!")
                }
            }
        }
        FPScanner.setOnClickListener {
            val intent = Intent(this, FingerPrintScanner::class.java)
            startActivity(intent)
        }
    }
    private fun notifyUser(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}