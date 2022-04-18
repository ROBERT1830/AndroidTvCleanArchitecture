package com.robertconstantindinescu.mytvapp.feature_browser.domain.use_case

data class BrowserUseCases(
    val searchNewMovieUseCase: SearchNewMovieUseCase,
    val getMovieUseCase: GetMovieUseCase,
    val getMoviesUseCase: GetMoviesUseCase,
    val trackMovieUseCase: TrackMovieUseCase,
    val searchOldMovieUseCase: SearchOldMovieUseCase,
    val categorizeOldMovie: CategorizeOldMovie
)