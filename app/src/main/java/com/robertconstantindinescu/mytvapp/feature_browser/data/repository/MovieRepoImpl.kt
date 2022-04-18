package com.robertconstantindinescu.mytvapp.feature_browser.data.repository

import android.app.Application
import android.content.Context
import com.robertconstantindinescu.mytvapp.R
import com.robertconstantindinescu.mytvapp.feature_browser.data.local.MovieDao
import com.robertconstantindinescu.mytvapp.feature_browser.data.local.json.OldMovieParser
import com.robertconstantindinescu.mytvapp.feature_browser.data.mapper.toMovieEntity
import com.robertconstantindinescu.mytvapp.feature_browser.data.mapper.toSharedMovie
import com.robertconstantindinescu.mytvapp.feature_browser.data.mapper.toSharedOldMovie
import com.robertconstantindinescu.mytvapp.feature_browser.data.remote.MovieApi
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.new_shared_movie.SharedNewMovie
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.old_shared_movie.SharedOldMovie
import com.robertconstantindinescu.mytvapp.feature_browser.domain.repository.MovieRepo
import com.robertconstantindinescxu.mytvapp.feature_browser.data.mapper.toSharedMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.Exception

class MovieRepoImpl(
    private val api: MovieApi,
    private val dao: MovieDao,
    private val json: OldMovieParser
): MovieRepo {

    /************NEW MOVIES DATA OPERATION******************/
    override suspend fun searchMovieApi(): Result<List<SharedNewMovie>> {

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

    override suspend fun insertMovie(sharedNewMovie: SharedNewMovie) {
        dao.insertMovie(sharedNewMovie.toMovieEntity())
    }

    override suspend fun getAllMovies(): Flow<List<SharedNewMovie>> {
        return dao.getAllMovies().map { entity ->
            entity.map { movie ->
                movie.toSharedMovie()
            }

        }
    }

    /************OLD MOVIES DATA OPERATION******************/

    override suspend fun searchOldMovieJson(context: Context): List<SharedOldMovie> {
        val inputStream = context.resources.openRawResource(R.raw.api)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        return json.loadVideosFromJson(jsonString).map {
            it.toSharedOldMovie()
        }
    }
}

































