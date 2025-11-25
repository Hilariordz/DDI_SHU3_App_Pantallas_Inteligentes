package com.example.streamverse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artwork(
    val title: String,
    val artist: String,
    val imageUrl: String
) : Parcelable
