package com.example.edukatalogapp

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgDetail = findViewById<ImageView>(R.id.imgDetailCourse)
        val tvTitle = findViewById<TextView>(R.id.tvDetailTitle)
        val tvCategory = findViewById<TextView>(R.id.tvDetailCategory)
        val tvDescription = findViewById<TextView>(R.id.tvDetailDescription)

        // Ambil data Parcelable yang dikirim dari MainActivity
        val course = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("EXTRA_COURSE", Course::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("EXTRA_COURSE")
        }

        course?.let {
            tvTitle.text = it.title
            tvCategory.text = it.category
            tvDescription.text = it.description
            imgDetail.setImageResource(it.imageResId)
        }
    }
}
