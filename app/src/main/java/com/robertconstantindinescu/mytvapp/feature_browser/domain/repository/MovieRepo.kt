package com.robertconstantindinescu.mytvapp.feature_browser.domain.repository

import com.robertconstantindinescu.mytvapp.feature_browser.data.remote.dto.Movie
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.SharedMovie
import kotlinx.coroutines.flow.Flow


interface MovieRepo {

    suspend fun searchMovieApi(): Result<List<SharedMovie>>

    suspend fun insertMovie(sharedMovie: SharedMovie)

    suspend fun getAllMovies(): Flow<List<SharedMovie>>

}