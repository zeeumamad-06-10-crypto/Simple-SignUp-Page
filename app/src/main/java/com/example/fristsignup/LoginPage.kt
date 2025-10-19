package com.example.fristsignup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginPage : AppCompatActivity() {

    private lateinit var registerBtn: Button
    private lateinit var loginBtn: Button
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_page)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 🔹 Initialize views
        registerBtn = findViewById(R.id.btnRegester)
        loginBtn = findViewById(R.id.btnLogin)
        nameEditText = findViewById(R.id.etName)
        emailEditText = findViewById(R.id.etEmail)
        passwordEditText = findViewById(R.id.etPassword)

        // 🔹 Register button → Go to Create Account page
        registerBtn.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        // 🔹 Login button → Validate inputs
        loginBtn.setOnClickListener {
            validateLogin()
        }
    }

    private fun validateLogin() {
        val name = nameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        // Name validation
        if (name.isEmpty()) {
            nameEditText.error = "Please enter your name"
            nameEditText.requestFocus()
            return
        }

        // Email validation
        if (email.isEmpty()) {
            emailEditText.error = "Please enter your email"
            emailEditText.requestFocus()
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.error = "Please enter a valid email"
            emailEditText.requestFocus()
            return
        }

        // Password validation
        if (password.isEmpty()) {
            passwordEditText.error = "Please enter your password"
            passwordEditText.requestFocus()
            return
        }
        if (password.length < 6) {
            passwordEditText.error = "Password must be at least 6 characters"
            passwordEditText.requestFocus()
            return
        }

        // ✅ If everything is valid
        Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()

        // Go to next page
        val intent = Intent(this, Personal::class.java)
        startActivity(intent)
    }
}
