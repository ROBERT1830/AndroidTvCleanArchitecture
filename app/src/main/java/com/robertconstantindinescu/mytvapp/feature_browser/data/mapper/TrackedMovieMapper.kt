package com.robertconstantindinescu.mytvapp.feature_browser.data.mapper

import com.robertconstantindinescu.mytvapp.feature_browser.data.local.entity.MovieEntity
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.new_shared_movie.SharedNewMovie

fun MovieEntity.toSharedMovie(): SharedNewMovie {
    return SharedNewMovie(
        actors = actors,
        description = description,
        directors = directors,
        genre = genre,
        imageUrl = imageUrl,
        name = name,
        rating = rating,
        thumbUrl = thumbUrl,
        year = year,
        id = id
    )
}

fun SharedNewMovie.toMovieEntity(): MovieEntity{
    return MovieEntity(
        actors = actors,
        description = description,
        directors = directors,
        genre = genre,
        imageUrl = imageUrl,
        name = name,
        rating = rating,
        thumbUrl = thumbUrl,
        year = year,
        id = id
    )
}