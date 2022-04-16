package com.robertconstantindinescu.mytvapp.feature_browser.domain.model

data class Category(
    val id: Long,
    val genre: String,
    val movies: List<SharedMovie>
)
