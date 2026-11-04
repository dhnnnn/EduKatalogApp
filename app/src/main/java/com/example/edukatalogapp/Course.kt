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
