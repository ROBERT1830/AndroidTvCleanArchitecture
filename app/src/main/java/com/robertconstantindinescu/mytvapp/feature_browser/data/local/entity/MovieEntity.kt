package com.robertconstantindinescu.mytvapp.feature_browser.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieEntity(
    val actors: List<String>,
    val description: String,
    val directors: List<String>,
    val genre: List<String>,
    val imageUrl: String,
    val name:String,
    val rating: Double,
    val thumbUrl: String,
    val year: Int?,
    @PrimaryKey val id: Long? = null
)
