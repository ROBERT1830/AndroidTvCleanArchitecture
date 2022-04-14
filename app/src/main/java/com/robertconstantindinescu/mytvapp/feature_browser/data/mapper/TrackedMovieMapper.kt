package com.robertconstantindinescu.mytvapp.feature_browser.data.mapper

import com.robertconstantindinescu.mytvapp.feature_browser.data.local.entity.MovieEntity
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.SharedMovie

fun MovieEntity.toSharedMovie(): SharedMovie{
    return SharedMovie(
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

fun SharedMovie.toMovieEntity(): MovieEntity{
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