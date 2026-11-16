
package com.example.pawcare

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val search = findViewById<EditText>(R.id.etSearchArticle)

        val title1 = findViewById<TextView>(R.id.articleTitle1)
        val desc1 = findViewById<TextView>(R.id.articleDesc1)

        val title2 = findViewById<TextView>(R.id.articleTitle2)
        val desc2 = findViewById<TextView>(R.id.articleDesc2)

        val title3 = findViewById<TextView>(R.id.articleTitle3)
        val desc3 = findViewById<TextView>(R.id.articleDesc3)

        search.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence?, start: Int, before: Int, count: Int
            ) {
                val keyword = s.toString().trim().lowercase()

                val show1 = keyword.isEmpty() ||
                        "jaga kebersihan kucing tempat tidur kotak pasir bersih nyaman".contains(keyword)

                val show2 = keyword.isEmpty() ||
                        "pilih makanan sehat makanan kucing usia nutrisi air bersih".contains(keyword)

                val show3 = keyword.isEmpty() ||
                        "pemeriksaan kesehatan rutin perilaku dokter hewan".contains(keyword)

                title1.visibility = if (show1) View.VISIBLE else View.GONE
                desc1.visibility = if (show1) View.VISIBLE else View.GONE

                title2.visibility = if (show2) View.VISIBLE else View.GONE
                desc2.visibility = if (show2) View.VISIBLE else View.GONE

                title3.visibility = if (show3) View.VISIBLE else View.GONE
                desc3.visibility = if (show3) View.VISIBLE else View.GONE
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        val navHome = findViewById<TextView>(R.id.navHome)
        val navProfile = findViewById<TextView>(R.id.navProfile)

        navHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        navProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }
    }
}
