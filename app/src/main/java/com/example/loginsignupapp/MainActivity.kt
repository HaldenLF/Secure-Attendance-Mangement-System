package com.example.loginsignupapp
// This app was made with the assistance of the following resources
//
// Coding Tutorials. (2020, June 2nd). Login and Register Form using SQLite Database in Android Studio | login registration android studio.
// YouTube[Video]. https://www.youtube.com/watch?v=8obgNNlj3Eo&t=833s&ab_channel=CodingTutorials
//
// Indently. (2020, May 11th). Biometric Authentication / Fingerprint Scanner (Android Studio Tutorial).
// YouTube[Video]. https://www.youtube.com/watch?v=tXHWyt8g5jc&t=140s&ab_channel=Indently
//
// Google Gemini. (n.d.). Gemini AI. google.com. https://gemini.google.com/app
//
//
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.loginsignupapp.ui.login.LoginActivity
import android.util.Patterns
class MainActivity : AppCompatActivity() {
    lateinit var username: EditText
    lateinit var userEmail: EditText
    lateinit var password: EditText
    lateinit var repassword: EditText
    lateinit var signup: Button
    lateinit var signin: Button
    lateinit var DB: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        repassword = findViewById(R.id.repassword)
        userEmail = findViewById(R.id.userEmail)
        signup = findViewById(R.id.btnsignup)
        signin = findViewById(R.id.btnsignin)
        DB = DBHelper(this)

        signup.setOnClickListener {
            val user = username.text.toString()
            val userEmail = userEmail.text.toString()
            val isEmailValid = isValidEmail(userEmail)
            val pass = password.text.toString()
            val repass = repassword.text.toString()

            if (user.isEmpty() || pass.isEmpty() || !isEmailValid) {
                notifyUser("Please enter all the fields and make sure your email is valid.")
            }
            else {
                if(pass == repass){
                    if(!DB.checkUsername(user)){
                        if(DB.insertData(user, pass, userEmail)) {
                            notifyUser("Registered Successfully!")
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                        }
                        else {
                            notifyUser("Registration Failed")
                        }
                    }
                    else {
                        notifyUser("User already exists, please sign in!")
                    }
                }
                else {
                    notifyUser("Login details incorrect")
                }
            }
        }
        signin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun isValidEmail (userEmail:String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()
    }
    private fun notifyUser(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}