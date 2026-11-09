**(EduKatalogApp)** menggunakan **Kotlin** dan **Android Studio**. 

**1\. Data Model (Course.kt)**  
Buat file Kotlin baru dengan nama Course.kt untuk menyimpan data item (implementasi Parcelable agar mudah dikirim antar-Activity). 

Kotlin  
package com.example.edukatalogapp

import android.os.Parcelable  
import kotlinx.parcelize.Parcelize

@Parcelize  
data class Course(  
    val title: String,  
    val category: String,  
    val description: String,  
    val imageResId: Int  
) : Parcelable  
**Catatan:** Pastikan plugin id 'kotlin-parcelize' sudah aktif di file build.gradle.kts (Module :app).

**2\. Halaman 1: Login Activity (activity\_login.xml & LoginActivity.kt)**  
**Layout (res/layout/activity\_login.xml)**  
XML  
\<?xml version="1.0" encoding="utf-8"?\>  
\<LinearLayout xmlns:android="http\://schemas.android.com/apk/res/android"  
    android:layout\_width="match\_parent"  
    android:layout\_height="match\_parent"  
    android:orientation="vertical"  
    android:padding="24dp"  
    android:gravity="center"\>

    \<TextView  
        android:layout\_width="wrap\_content"  
        android:layout\_height="wrap\_content"  
        android:text="EduKatalog"  
        android:textSize="28sp"  
        android:textStyle="bold"  
        android:layout\_marginBottom="32dp"/\>

    \<EditText  
        android:id="@+id/etUsername"  
        android:layout\_width="match\_parent"  
        android:layout\_height="wrap\_content"  
        android:hint="Username"  
        android:inputType="text"  
        android:layout\_marginBottom="16dp"/\>

    \<EditText  
        android:id="@+id/etPassword"  
        android:layout\_width="match\_parent"  
        android:layout\_height="wrap\_content"  
        android:hint="Password"  
        android:inputType="textPassword"  
        android:layout\_marginBottom="24dp"/\>  
    \<Button  
        android:id="@+id/btnLogin"  
        android:layout\_width="match\_parent"  
        android:layout\_height="wrap\_content"  
        android:text="Login"/\>

\</LinearLayout\>  
**Logic (LoginActivity.kt)**  
Kotlin  
package com.example.edukatalogapp

import android.content.Intent  
import android.os.Bundle  
import android.widget.Button  
import android.widget.EditText  
import android.widget.Toast  
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {  
        super.onCreate(savedInstanceState)  
        setContentView(R.layout.activity\_login)

        val etUsername \= findViewById\<EditText\>(R.id.etUsername)  
        val etPassword \= findViewById\<EditText\>(R.id.etPassword)  
        val btnLogin \= findViewById\<Button\>(R.id.btnLogin)

        btnLogin.setOnClickListener {  
            val username \= etUsername.text.toString().trim()  
            val password \= etPassword.text.toString().trim()

            // Validasi input sederhana  
            if (username.isEmpty()) {  
                etUsername.error \= "Username tidak boleh kosong"  
                return@setOnClickListener  
            }  
            if (password.isEmpty()) {  
                etPassword.error \= "Password tidak boleh kosong"  
                return@setOnClickListener  
            }

            // Pindah ke MainActivity dengan melempar data Intent Extra  
            val intent \= Intent(this, MainActivity::class.java)  
            intent.putExtra("EXTRA\_USERNAME", username)  
            startActivity(intent)  
            finish()  
        }  
    }  
}

**3\. Item List & Adapter (item\_course.xml & CourseAdapter.kt)**  
**Layout List Item (res/layout/item\_course.xml)**  
XML  
\<?xml version="1.0" encoding="utf-8"?\>  
\<androidx.cardview.widget.CardView xmlns:android="http\://schemas.android.com/apk/res/android"  
    xmlns:app="http\://schemas.android.com/apk/res-auto"  
    android:layout\_width="match\_parent"  
    android:layout\_height="wrap\_content"  
    android:layout\_margin="8dp"  
    app:cardCornerRadius="8dp"  
    app:cardElevation="4dp"\>

    \<LinearLayout  
        android:layout\_width="match\_parent"  
        android:layout\_height="wrap\_content"  
        android:orientation="horizontal"  
        android:padding="12dp"\>

        \<ImageView  
            android:id="@+id/imgCourse"  
            android:layout\_width="80dp"  
            android:layout\_height="80dp"  
            android:scaleType="centerCrop"  
            android:src="@drawable/ic\_launcher\_background"/\>

        \<LinearLayout  
            android:layout\_width="match\_parent"  
            android:layout\_height="wrap\_content"  
            android:orientation="vertical"  
            android:layout\_marginStart="16dp"\>

            \<TextView  
                android:id="@+id/tvTitle"  
                android:layout\_width="wrap\_content"  
                android:layout\_height="wrap\_content"  
                android:textSize="18sp"  
                android:textStyle="bold"  
                android:text="Judul Matakuliah"/\>

            \<TextView  
                android:id="@+id/tvCategory"  
                android:layout\_width="wrap\_content"  
                android:layout\_height="wrap\_content"  
                android:textSize="14sp"  
                android:textColor="\#666666"  
                android:text="Kategori"  
                android:layout\_marginTop="4dp"/\>

        \</LinearLayout\>  
    \</LinearLayout\>  
\</androidx.cardview.widget.CardView\>

**Adapter (CourseAdapter.kt)**  
Kotlin  
package com.example.edukatalogapp

import android.view.LayoutInflater  
import android.view.View  
import android.view.ViewGroup  
import android.widget.ImageView  
import android.widget.TextView  
import androidx.recyclerview.widget.RecyclerView

class CourseAdapter(  
    private val courseList: List\<Course\>,  
    private val onItemClick: (Course) \-\> Unit  
) : RecyclerView.Adapter\<CourseAdapter.CourseViewHolder\>() {

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {  
        val imgCourse: ImageView \= itemView.findViewById(R.id.imgCourse)  
        val tvTitle: TextView \= itemView.findViewById(R.id.tvTitle)  
        val tvCategory: TextView \= itemView.findViewById(R.id.tvCategory)  
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {  
        val view \= LayoutInflater.from(parent.context).inflate(R.layout.item\_course, parent, false)  
        return CourseViewHolder(view)  
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {  
        val course \= courseList\[position\]  
        holder.tvTitle.text \= course.title  
        holder.tvCategory.text \= course.category  
        holder.imgCourse.setImageResource(course.imageResId)

        holder.itemView.setOnClickListener {  
            onItemClick(course)  
        }  
    }

    override fun getItemCount(): Int \= courseList.size  
}

**4\. Halaman 2: Dashboard/Katalog (activity\_main.xml & MainActivity.kt)**  
**Layout (res/layout/activity\_main.xml)**  
XML  
\<?xml version="1.0" encoding="utf-8"?\>  
\<LinearLayout xmlns:android="http\://schemas.android.com/apk/res/android"  
    android:layout\_width="match\_parent"  
    android:layout\_height="match\_parent"  
    android:orientation="vertical"  
    android:padding="16dp"\>

    \<TextView  
        android:id="@+id/tvWelcome"  
        android:layout\_width="wrap\_content"  
        android:layout\_height="wrap\_content"  
        android:textSize="20sp"  
        android:textStyle="bold"  
        android:text="Selamat Datang\!"  
        android:layout\_marginBottom="16dp"/\>

    \<androidx.recyclerview.widget.RecyclerView  
        android:id="@+id/rvCourses"  
        android:layout\_width="match\_parent"  
        android:layout\_height="match\_parent"/\>

\</LinearLayout\>  
**Logic (MainActivity.kt)**  
Kotlin  
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
        setContentView(R.layout.activity\_main)

        val tvWelcome \= findViewById\<TextView\>(R.id.tvWelcome)  
        val rvCourses \= findViewById\<RecyclerView\>(R.id.rvCourses)

        // Ambil data username dari Intent  
        val username \= intent.getStringExtra("EXTRA\_USERNAME") ?: "User"  
        tvWelcome.text \= "Selamat Datang, \$username\!"

        // Data dummy 5 item (sesuai spesifikasi UTS)  
        val dummyData \= listOf(  
            Course("Pemrograman Mobile", "Teknik Informatika", "Mempelajari pengembangan aplikasi Android menggunakan Kotlin dan Android Studio.", R.drawable.ic\_launcher\_foreground),  
            Course("Pemrograman Web", "Teknik Informatika", "Membangun aplikasi web interaktif menggunakan HTML, CSS, JavaScript, dan framework modern.", R.drawable.ic\_launcher\_foreground),  
            Course("Basis Data", "Sistem Informasi", "Konsep perancangan relasional database, pengoperasian query SQL, dan optimasi data.", R.drawable.ic\_launcher\_foreground),  
            Course("Struktur Data", "Teknik Informatika", "Pemahaman mengenai array, stack, queue, tree, graph, serta algoritma pemrosesan data.", R.drawable.ic\_launcher\_foreground),  
            Course("Jaringan Komputer", "Teknik Informatika", "Konsep dasar topologi jaringan, protokol TCP/IP, routing, dan pengamanan jaringan.", R.drawable.ic\_launcher\_foreground)  
        )

        // Setup RecyclerView  
        rvCourses.layoutManager \= LinearLayoutManager(this)  
        rvCourses.adapter \= CourseAdapter(dummyData) { selectedCourse \-\>  
            // Event klik: Lempar object Parcelable ke DetailActivity  
            val intent \= Intent(this, DetailActivity::class.java)  
            intent.putExtra("EXTRA\_COURSE", selectedCourse)  
            startActivity(intent)  
        }  
    }  
}

**5\. Halaman 3: Detail Data (activity\_detail.xml & DetailActivity.kt)**  
**Layout (res/layout/activity\_detail.xml)**  
XML  
\<?xml version="1.0" encoding="utf-8"?\>  
\<ScrollView xmlns:android="http\://schemas.android.com/apk/res/android"  
    android:layout\_width="match\_parent"  
    android:layout\_height="match\_parent"  
    android:padding="16dp"\>

    \<LinearLayout  
        android:layout\_width="match\_parent"  
        android:layout\_height="wrap\_content"  
        android:orientation="vertical"\>

        \<ImageView  
            android:id="@+id/imgDetailCourse"  
            android:layout\_width="match\_parent"  
            android:layout\_height="200dp"  
            android:scaleType="fitCenter"  
            android:src="@drawable/ic\_launcher\_background"  
            android:layout\_marginBottom="16dp"/\>

        \<TextView  
            android:id="@+id/tvDetailTitle"  
            android:layout\_width="wrap\_content"  
            android:layout\_height="wrap\_content"  
            android:textSize="24sp"  
            android:textStyle="bold"  
            android:text="Judul Detail"/\>

        \<TextView  
            android:id="@+id/tvDetailCategory"  
            android:layout\_width="wrap\_content"  
            android:layout\_height="wrap\_content"  
            android:textSize="16sp"  
            android:textColor="\#666666"  
            android:text="Kategori"  
            android:layout\_marginTop="4dp"  
            android:layout\_marginBottom="16dp"/\>

        \<TextView  
            android:id="@+id/tvDetailDescription"  
            android:layout\_width="wrap\_content"  
            android:layout\_height="wrap\_content"  
            android:textSize="16sp"  
            android:lineSpacingExtra="4dp"  
            android:text="Deskripsi lengkap mengenai materi mata kuliah ini."/\>

    \</LinearLayout\>  
\</ScrollView\>

**Logic (DetailActivity.kt)**  
Kotlin  
package com.example.edukatalogapp

import android.os.Build  
import android.os.Bundle  
import android.widget.ImageView  
import android.widget.TextView  
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {  
        super.onCreate(savedInstanceState)  
        setContentView(R.layout.activity\_detail)

        val imgDetail \= findViewById\<ImageView\>(R.id.imgDetailCourse)  
        val tvTitle \= findViewById\<TextView\>(R.id.tvDetailTitle)  
        val tvCategory \= findViewById\<TextView\>(R.id.tvDetailCategory)  
        val tvDescription \= findViewById\<TextView\>(R.id.tvDetailDescription)

        // Ambil data Parcelable yang dikirim dari MainActivity  
        val course \= if (Build.VERSION.SDK\_INT \>= Build.VERSION\_CODES.TIRAMISU) {  
            intent.getParcelableExtra("EXTRA\_COURSE", Course::class.java)  
        } else {  
            @Suppress("DEPRECATION")  
            intent.getParcelableExtra("EXTRA\_COURSE")  
        }

        course?.let {  
            tvTitle.text \= it.title  
            tvCategory.text \= it.category  
            tvDescription.text \= it.description  
            imgDetail.setImageResource(it.imageResId)  
        }  
    }  
}

**6\. Konfigurasi AndroidManifest.xml**  
Pastikan ketiga Activity terdaftar dan LoginActivity dijadikan sebagai launcher/halaman awal aplikasi:   
XML  
\<?xml version="1.0" encoding="utf-8"?\>  
\<manifest xmlns:android="http\://schemas.android.com/apk/res/android"\>

    \<application  
        android:allowBackup="true"  
        android:icon="@mipmap/ic\_launcher"  
        android:label="EduKatalogApp"  
        android:roundIcon="@mipmap/ic\_launcher\_round"  
        android:supportsRtl="true"  
        android:theme="@style/Theme.AppCompat.Light.DarkActionBar"\>

        \<\!-- LoginActivity sebagai Launcher (Halaman Utama yang dibuka pertama) \--\>  
        \<activity  
            android:name=".LoginActivity"  
            android:exported="true"\>  
            \<intent-filter\>  
                \<action android:name="android.intent.action.MAIN" /\>  
                \<category android:name="android.intent.category.LAUNCHER" /\>  
            \</intent-filter\>  
        \</activity\>

        \<activity android:name=".MainActivity" /\>  
        \<activity android:name=".DetailActivity" /\>

    \</application\>

\</manifest\>

**Langkah 1: Membuat Proyek Baru di Android Studio**

1. Buka **Android Studio**.  
2. Pilih **New Project** \> **Empty Views Activity** (atau *Empty Activity*), lalu klik **Next**.  
3. Isi konfigurasi proyek:

**Name:** EduKatalogApp

* **Package name:** com.example.edukatalogapp  
  * **Language:** Kotlin  
  * **Minimum SDK:** API 24 (Android 7.0) atau versi di atasnya.  
4. Klik **Finish** dan tunggu hingga proses sinkronisasi Gradle (*Gradle Build*) selesai.

**Langkah 2: Mengaktifkan Plugin kotlin-parcelize**

1. Buka file **build.gradle.kts (Module :app)** di panel kiri (*Project Explorer*).  
2. Tambahkan plugin kotlin-parcelize di bagian plugins:

Kotlin  
plugins {  
    alias(libs.plugins.android.application)  
    alias(libs.plugins.kotlin.android)  
    id("kotlin-parcelize") // Tambahkan baris ini  
}

3. Klik tombol **Sync Now** di pojok kanan atas layar Android Studio.

**Langkah 3: Membuat File Model Data (Course.kt)**

1. Klik kanan pada package com.example.edukatalogapp \> **New** \> **Kotlin Class/File**.  
2. Pilih **Class**, beri nama Course.  
3. Masukkan kode data class Course yang telah mengimplementasikan @Parcelize.

**Langkah 4: Membuat LoginActivity (Halaman 1\)**

1. **Layout:** Buka file res/layout/activity\_main.xml (atau ubah nama/buat layout baru activity\_login.xml). Tempelkan kode XML form login (EditText Username, Password, dan Button Login).  
2. **Class:** Buka file MainActivity.kt (atau ganti nama menjadi LoginActivity.kt). Salin kode logika validasi input dan intent perpindahan ke MainActivity.

**Langkah 5: Membuat CourseAdapter & Layout Item List**

1. **Layout Item:** Klik kanan folder res/layout \> **New** \> **Layout Resource File**.  
   * Beri nama: item\_course.xml.  
   * Desain tampilan item menggunakan CardView, ImageView, dan TextView.

2. **Adapter:** Klik kanan package com.example.edukatalogapp \> **New** \> **Kotlin Class/File**.  
   * Beri nama: CourseAdapter.  
   * Masukkan kode logika adapter RecyclerView dan event handling klik item.

**Langkah 6: Membuat MainActivity (Halaman Dashboard/Katalog)**

1. **Activity Baru:** Klik kanan package com.example.edukatalogapp \> **New** \> **Activity** \> **Empty Views Activity**.  
   * Beri nama: MainActivity.

2. **Layout:** Edit file res/layout/activity\_main.xml dengan menambahkan TextView sambutan dan komponen RecyclerView.

3. **Class:** Buka MainActivity.kt, lalu tambahkan data dummy 5 item serta inisialisasi LinearLayoutManager dan CourseAdapter.

**Langkah 7: Membuat DetailActivity (Halaman Detail)**

1. **Activity Baru:** Klik kanan package com.example.edukatalogapp \> **New** \> **Activity** \> **Empty Views Activity**.  
   * Beri nama: DetailActivity.  
2. **Layout:** Edit file res/layout/activity\_detail.xml dengan susunan ScrollView, ImageView, TextView judul, kategori, dan deskripsi detail.  
3. **Class:** Buka DetailActivity.kt, lalu tambahkan kode untuk menerima data *Parcelable* dari Intent dan menampilkan data tersebut ke layar.

**Langkah 8: Memeriksa Pendaftaran Activity di AndroidManifest.xml**

1. Buka file app/manifests/AndroidManifest.xml.  
2. Pastikan LoginActivity memiliki tag \<intent-filter\> agar menjadi halaman pertama yang dijalankan saat aplikasi dibuka:

XML  
\<activity  
    android:name=".LoginActivity"  
    android:exported="true"\>  
    \<intent-filter\>  
        \<action android:name="android.intent.action.MAIN" /\>  
        \<category android:name="android.intent.category.LAUNCHER" /\>  
    \</intent-filter\>  
\</activity\>

3. Pastikan MainActivity dan DetailActivity juga sudah terdaftar di dalam manifest.

**Langkah 9: Menjalankan dan Menguji Aplikasi**

1. Hubungkan **Android Emulator** atau perangkat HP Android via kabel data (*USB Debugging* aktif).  
2. Klik tombol hijau **Run 'app'** (tombol Play) di bagian atas toolbar Android Studio.  
3. **Pengujian Fungsionalitas:**  
   * Coba tekan tombol **Login** dalam keadaan form kosong (layar harus menampilkan pesan error).  
   * Isi Username & Password, lalu tekan **Login** (berpindah ke MainActivity menampilkan nama user).  
   * Gulir daftar item di RecyclerView, lalu klik salah satu item.  
   * Pastikan aplikasi berpindah ke DetailActivity dan menampilkan detail materi sesuai item yang diklik.