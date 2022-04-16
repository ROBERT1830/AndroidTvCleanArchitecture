package com.robertconstantindinescu.mytvapp.feature_browser.domain.use_case

data class BrowserUseCases(
    val searchMovieUseCase: SearchMovieUseCase,
    val getMovieUseCase: GetMovieUseCase,
    val getMoviesUseCase: GetMoviesUseCase,
    val trackMovieUseCase: TrackMovieUseCase
)