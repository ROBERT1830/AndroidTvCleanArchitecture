package com.robertconstantindinescu.mytvapp.feature_browser.presentation.home_screen

import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.new_shared_movie.SharedNewMovie

data class BrowserScreenState(
    val newMovieList: List<SharedNewMovie> = listOf()
    // TODO: could be added more
)
