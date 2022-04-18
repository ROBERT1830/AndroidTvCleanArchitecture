package com.robertconstantindinescxu.mytvapp.feature_browser.data.mapper

import com.robertconstantindinescu.mytvapp.feature_browser.data.remote.dto.new_movies.Movie
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.new_shared_movie.SharedNewMovie

fun Movie.toSharedMovie(): SharedNewMovie? {

    return SharedNewMovie(
        actors = actors,
        description = desc,
        directors = directors,
        genre = genre,
        imageUrl = imageUrl,
        name = name,
        rating = rating,
        thumbUrl = thumbUrl,
        year = year,
        id = categoryId
    )
}