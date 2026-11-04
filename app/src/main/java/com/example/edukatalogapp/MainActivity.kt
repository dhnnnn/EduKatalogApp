package com.example.edukatalogapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val rvCourses = findViewById<RecyclerView>(R.id.rvCourses)

        // Ambil data username dari Intent
        val username = intent.getStringExtra("EXTRA_USERNAME") ?: "User"
        tvWelcome.text = "Selamat Datang, $username!"

        // Data dummy 5 item (sesuai spesifikasi UTS)
        val dummyData = listOf(
            Course("Pemrograman Mobile", "Teknik Informatika", "Mempelajari pengembangan aplikasi Android menggunakan Kotlin dan Android Studio.", R.drawable.ic_launcher_foreground),
            Course("Pemrograman Web", "Teknik Informatika", "Membangun aplikasi web interaktif menggunakan HTML, CSS, JavaScript, dan framework modern.", R.drawable.ic_launcher_foreground),
            Course("Basis Data", "Sistem Informasi", "Konsep perancangan relasional database, pengoperasian query SQL, dan optimasi data.", R.drawable.ic_launcher_foreground),
            Course("Struktur Data", "Teknik Informatika", "Pemahaman mengenai array, stack, queue, tree, graph, serta algoritma pemrosesan data.", R.drawable.ic_launcher_foreground),
            Course("Jaringan Komputer", "Teknik Informatika", "Konsep dasar topologi jaringan, protokol TCP/IP, routing, dan pengamanan jaringan.", R.drawable.ic_launcher_foreground)
        )

        // Setup RecyclerView
        rvCourses.layoutManager = LinearLayoutManager(this)
        rvCourses.adapter = CourseAdapter(dummyData) { selectedCourse ->
            // Event klik: Lempar object Parcelable ke DetailActivity
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("EXTRA_COURSE", selectedCourse)
            startActivity(intent)
        }
    }
}
