package com.robertconstantindinescu.mytvapp.feature_browser.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.robertconstantindinescu.mytvapp.feature_browser.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getAllMovies():Flow<List<MovieEntity>>

    @Insert
    suspend fun insertMovie(movieEntity: MovieEntity)
}