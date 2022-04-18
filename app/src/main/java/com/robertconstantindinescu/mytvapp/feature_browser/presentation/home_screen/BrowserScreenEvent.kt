package com.robertconstantindinescu.mytvapp.feature_browser.presentation.home_screen

import com.robertconstantindinescu.mytvapp.feature_browser.data.remote.dto.new_movies.Movie

sealed class BrowserScreenEvent{
    object SearchMovies: BrowserScreenEvent()
    object OnOldMoviesHeaderClick: BrowserScreenEvent()
    data class OnMovieClick(val selectedMovie: Movie) : BrowserScreenEvent()


}
