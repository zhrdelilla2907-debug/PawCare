package com.example.pawcare

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.text.method.HideReturnsTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.etEmail)
        val password = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnTogglePassword =
            findViewById<TextView>(R.id.btnTogglePassword)

        val emailBenar = "zahraadel@gmail.com"
        val passwordBenar = "zahracantik"

        var passwordTerlihat = false

        // Fitur lihat dan sembunyikan password
        btnTogglePassword.setOnClickListener {
            passwordTerlihat = !passwordTerlihat

            if (passwordTerlihat) {
                password.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                btnTogglePassword.text = "Sembunyi"
            } else {
                password.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                btnTogglePassword.text = "Lihat"
            }

            password.setSelection(password.text.length)
        }

        // Proses login
        btnLogin.setOnClickListener {
            val inputEmail = email.text.toString().trim()
            val inputPassword = password.text.toString()

            if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
                Toast.makeText(
                    this,
                    "Email dan kata sandi harus diisi",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (
                inputEmail == emailBenar &&
                inputPassword == passwordBenar
            ) {
                val sharedPref =
                    getSharedPreferences("PawCareData", MODE_PRIVATE)

                sharedPref.edit()
                    .putString("userEmail", emailBenar)
                    .putString(
                        "userName",
                        sharedPref.getString("userName", "Zahra")
                    )
                    .apply()

                Toast.makeText(
                    this,
                    "Login berhasil! Selamat datang di PawCare 🐾",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(
                    this,
                    "Email atau kata sandi salah!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
