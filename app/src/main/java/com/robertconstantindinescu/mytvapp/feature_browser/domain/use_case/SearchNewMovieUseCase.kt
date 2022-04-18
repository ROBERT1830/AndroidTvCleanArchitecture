package com.robertconstantindinescu.mytvapp.feature_browser.domain.use_case

import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.new_shared_movie.SharedNewMovie
import com.robertconstantindinescu.mytvapp.feature_browser.domain.repository.MovieRepo

class SearchNewMovieUseCase(
    private val repository: MovieRepo
) {
    suspend operator fun invoke(): Result<List<SharedNewMovie>>{
        return  repository.searchMovieApi()
    }
}