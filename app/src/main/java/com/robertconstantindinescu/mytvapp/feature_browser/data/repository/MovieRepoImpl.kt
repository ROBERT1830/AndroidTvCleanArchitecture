package com.robertconstantindinescu.mytvapp.feature_browser.data.repository

import com.robertconstantindinescu.mytvapp.feature_browser.data.local.MovieDao
import com.robertconstantindinescu.mytvapp.feature_browser.data.mapper.toMovieEntity
import com.robertconstantindinescu.mytvapp.feature_browser.data.mapper.toSharedMovie
import com.robertconstantindinescu.mytvapp.feature_browser.data.remote.MovieApi
import com.robertconstantindinescu.mytvapp.feature_browser.data.remote.dto.Movie
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.SharedMovie
import com.robertconstantindinescu.mytvapp.feature_browser.domain.repository.MovieRepo
import com.robertconstantindinescxu.mytvapp.feature_browser.data.mapper.toSharedMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.Exception

class MovieRepoImpl(
    private val api: MovieApi,
    private val dao: MovieDao
): MovieRepo {

    override suspend fun searchMovieApi(): Result<List<SharedMovie>> {

        return try {



            Result.success(
                api.getMovies().mapNotNull {
                    it.toSharedMovie()
                }
            )
        }catch (e:Exception){
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertMovie(sharedMovie: SharedMovie) {
        dao.insertMovie(sharedMovie.toMovieEntity())
    }

    override suspend fun getAllMovies(): Flow<List<SharedMovie>> {
        return dao.getAllMovies().map { entity ->
            entity.map { movie ->
                movie.toSharedMovie()
            }

        }
    }
}