package com.example.practica7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practica7.activities.MainMenuActivity
import com.example.practica7.models.Order
import com.example.practica7.models.Product
import com.example.practica7.models.User
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    private lateinit var userTextField: TextInputEditText
    private lateinit var passwordTextField: TextInputEditText

    private lateinit var loginButton: Button
    private lateinit var exitButton: Button
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userTextField = findViewById(R.id.username_textfield)
        passwordTextField = findViewById(R.id.password_texfield)
        loginButton = findViewById(R.id.login_button)
        exitButton = findViewById(R.id.exit_button)
        getUser()

        loginButton.setOnClickListener {
            if(textFieldsValid()) {
                Toast.makeText(this, "Next activity", Toast.LENGTH_SHORT).show()
                saveUser()
                goToMainMenu()
            }
            else {
                Toast.makeText(this, "Empty field!", Toast.LENGTH_SHORT).show()
            }
        }

        exitButton.setOnClickListener {
            Toast.makeText(this, "Goodbye!", Toast.LENGTH_SHORT).show()
            this.finish()
            exitProcess(0)
        }
    }

    private fun getUser() {
        val pref = getSharedPreferences("User", MODE_PRIVATE)
        val jsonData = pref.getString("User", "")
        if(!jsonData.isNullOrEmpty()) {
            val gson = Gson()
            user = gson.fromJson(jsonData, User::class.java)
            userTextField.setText(user.userName)
            passwordTextField.setText(user.password)
        }
    }

    private fun saveUser() {
        val userName = userTextField.text.toString()
        val password = passwordTextField.text.toString()
        user = User(userName, password, ArrayList<Order>())
        val editor = getSharedPreferences("User", MODE_PRIVATE).edit()
        val gson = Gson()
        val jsonData = gson.toJson(user)
        editor.putString("User", jsonData)
        editor.commit()
    }

    private fun goToMainMenu() {
        val intent = Intent(this, MainMenuActivity::class.java).apply {
            val gson = Gson()
            val jsonData = gson.toJson(user)
            putExtra("User", jsonData)
        }
        startActivity(intent)
    }

    private fun textFieldsValid(): Boolean {
        if(userTextField.text.isNullOrBlank() || passwordTextField.text.isNullOrBlank()) {
            return false
        }
        return true
    }
}