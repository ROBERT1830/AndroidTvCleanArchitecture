package com.robertconstantindinescu.mytvapp.feature_browser.presentation.home_screen

import com.robertconstantindinescu.mytvapp.feature_browser.data.remote.dto.Movie

sealed class BrowserScreenEvent{
    object SearchMovies: BrowserScreenEvent()
    data class DetailNavigate(val selectedMovie: Movie) : BrowserScreenEvent()

}
