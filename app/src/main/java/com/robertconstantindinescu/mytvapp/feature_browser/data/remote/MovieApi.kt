package com.robertconstantindinescu.mytvapp.feature_browser.data.remote

import com.robertconstantindinescu.mytvapp.feature_browser.data.remote.dto.new_movies.Movie
import retrofit2.http.GET

interface MovieApi {

    @GET("top250_min.json")
    suspend fun getMovies(): List<Movie>
}