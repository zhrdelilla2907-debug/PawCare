package com.example.pawcare

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var imgProfile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        imgProfile = findViewById(R.id.imgProfile)

        findViewById<TextView>(R.id.navHome).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        findViewById<TextView>(R.id.navArticle).setOnClickListener {
            startActivity(Intent(this, ArticleActivity::class.java))
            finish()
        }

        findViewById<TextView>(R.id.btnEditProfile).setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
        }

        findViewById<TextView>(R.id.btnLogout).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        tampilkanProfil()
    }

    private fun tampilkanProfil() {
        val sharedPref = getSharedPreferences("PawCareData", MODE_PRIVATE)

        findViewById<TextView>(R.id.tvProfileName).text =
            sharedPref.getString("userName", "PawCare User")

        findViewById<TextView>(R.id.tvProfileEmail).text =
            sharedPref.getString("userEmail", "petlover@email.com")

        val foto = sharedPref.getString("profilePhoto", null)

        if (foto != null) {
            try {
                imgProfile.setImageURI(Uri.parse(foto))
            } catch (e: Exception) {
                imgProfile.setImageResource(R.drawable.banner_kucing)
            }
        } else {
            imgProfile.setImageResource(R.drawable.banner_kucing)
        }
    }

    override fun onResume() {
        super.onResume()

        if (::imgProfile.isInitialized) {
            tampilkanProfil()
        }
    }
}
