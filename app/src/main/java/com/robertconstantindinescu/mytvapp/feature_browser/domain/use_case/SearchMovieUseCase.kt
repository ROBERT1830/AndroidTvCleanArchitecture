package com.robertconstantindinescu.mytvapp.feature_browser.domain.use_case

import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.SharedMovie
import com.robertconstantindinescu.mytvapp.feature_browser.domain.repository.MovieRepo

class SearchMovieUseCase(
    private val repository: MovieRepo
) {
    suspend operator fun invoke(): Result<List<SharedMovie>>{
        return  repository.searchMovieApi()
    }
}