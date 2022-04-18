package com.robertconstantindinescu.mytvapp.feature_browser.domain.model.new_shared_movie

data class Category(
    val id: Long,
    val genre: String,
    val newMovies: List<SharedNewMovie>
)
