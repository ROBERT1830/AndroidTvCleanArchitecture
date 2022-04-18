package com.robertconstantindinescu.mytvapp.feature_browser.domain.repository

import android.app.Application
import android.content.Context
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.new_shared_movie.SharedNewMovie
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.old_shared_movie.SharedOldMovie
import kotlinx.coroutines.flow.Flow


interface MovieRepo {

    suspend fun searchMovieApi(): Result<List<SharedNewMovie>>

    suspend fun searchOldMovieJson(context: Context): List<SharedOldMovie>

    suspend fun insertMovie(sharedNewMovie: SharedNewMovie)

    suspend fun getAllMovies(): Flow<List<SharedNewMovie>>

}