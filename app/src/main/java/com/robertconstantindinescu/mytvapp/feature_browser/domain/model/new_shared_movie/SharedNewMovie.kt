package com.robertconstantindinescu.mytvapp.feature_browser.domain.model.new_shared_movie

import androidx.room.PrimaryKey

data class SharedNewMovie(
    val actors: List<String>,
    val description: String,
    val directors: List<String>,
    val genre: List<String>,
    val imageUrl: String,
    val name:String,
    val rating: Double,
    val thumbUrl: String,
    val year: Int?,
    var id: Long? = null
)
