package com.robertconstantindinescu.mytvapp.feature_browser.domain.model.old_shared_movie

data class Category(
    val id: Long,
    val type: String,
    val movie: List<SharedOldMovie>
)
