package com.example.pawcare

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        tampilkanNama()

        val navProfile = findViewById<TextView>(R.id.navProfile)
        val navArticle = findViewById<TextView>(R.id.navArticle)

        navProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        navArticle.setOnClickListener {
            startActivity(Intent(this, ArticleActivity::class.java))
        }
    }

    private fun tampilkanNama() {
        val sharedPref = getSharedPreferences("PawCareData", MODE_PRIVATE)
        val name = sharedPref.getString("userName", null)

        val tvHomeGreeting = findViewById<TextView>(R.id.tvHomeGreeting)

        tvHomeGreeting.text = if (name.isNullOrBlank()) {
            "Halo, Pecinta Kucing! 🐾"
        } else {
            "Halo, $name! 🐾"
        }
    }

    override fun onResume() {
        super.onResume()
        tampilkanNama()
    }
}

