package com.robertconstantindinescxu.mytvapp.feature_browser.data.mapper

import com.robertconstantindinescu.mytvapp.feature_browser.data.remote.dto.Movie
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.SharedMovie

fun Movie.toSharedMovie(): SharedMovie? {

    return SharedMovie(
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