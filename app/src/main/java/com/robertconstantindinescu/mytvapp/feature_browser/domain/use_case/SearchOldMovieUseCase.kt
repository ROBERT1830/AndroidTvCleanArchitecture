package com.robertconstantindinescu.mytvapp.feature_browser.domain.use_case

import android.app.Application
import android.content.Context
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.old_shared_movie.SharedOldMovie
import com.robertconstantindinescu.mytvapp.feature_browser.domain.repository.MovieRepo

// TODO:
class SearchOldMovieUseCase(private val repository: MovieRepo){

    suspend operator fun invoke(context: Context): List<SharedOldMovie>{
       return repository.searchOldMovieJson(context)
    }
}