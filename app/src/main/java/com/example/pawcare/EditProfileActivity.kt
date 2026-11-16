package com.example.pawcare

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {

    private lateinit var imgEditProfile: ImageView

    private val pilihFoto =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->

            if (uri != null) {
                try {
                    contentResolver.takePersistableUriPermission(
                        uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )

                    imgEditProfile.setImageURI(uri)

                    getSharedPreferences("PawCareData", MODE_PRIVATE)
                        .edit()
                        .putString("profilePhoto", uri.toString())
                        .apply()

                } catch (e: Exception) {
                    Toast.makeText(
                        this,
                        "Foto gagal dibuka. Silakan pilih foto lain.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val sharedPref = getSharedPreferences("PawCareData", MODE_PRIVATE)

        imgEditProfile = findViewById(R.id.imgEditProfile)

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnChangePhoto = findViewById<Button>(R.id.btnChangePhoto)

        val emailTetap = "zahraadel@gmail.com"

        etName.setText(sharedPref.getString("userName", "Zahra"))
        etEmail.setText(emailTetap)

        etEmail.isEnabled = false
        etEmail.isFocusable = false
        etEmail.isClickable = false

        val fotoTersimpan = sharedPref.getString("profilePhoto", null)

        if (fotoTersimpan != null) {
            try {
                imgEditProfile.setImageURI(Uri.parse(fotoTersimpan))
            } catch (e: Exception) {
                imgEditProfile.setImageResource(R.drawable.banner_kucing)
            }
        }

        btnChangePhoto.setOnClickListener {
            pilihFoto.launch(arrayOf("image/*"))
        }

        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(
                    this,
                    "Nama harus diisi",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                sharedPref.edit()
                    .putString("userName", name)
                    .putString("userEmail", emailTetap)
                    .apply()

                Toast.makeText(
                    this,
                    "Profil berhasil diperbarui!",
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
        }
    }
}
