package com.robertconstantindinescu.mytvapp.feature_browser.domain.model

import androidx.room.PrimaryKey

data class SharedMovie(
    val actors: List<String>,
    val description: String,
    val directors: List<String>,
    val genre: List<String>,
    val imageUrl: String,
    val name:String,
    val rating: Double,
    val thumbUrl: String,
    val year: Int?,
    val id: Long? = null
)
