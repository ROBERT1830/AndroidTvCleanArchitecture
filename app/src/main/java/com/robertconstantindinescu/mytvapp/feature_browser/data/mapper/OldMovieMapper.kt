package com.robertconstantindinescu.mytvapp.feature_browser.data.mapper

import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.OldMovie
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.old_shared_movie.OldMovieCategory
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.old_shared_movie.SharedOldMovie

fun OldMovie.toSharedOldMovie(): SharedOldMovie{
    return SharedOldMovie(
        id = id,
        name = name,
        description = description,
        uri = uri,
        videoUri = videoUri,
        thumbnailUri = thumbnailUri,
        oldMovieCategory = OldMovieCategory.fromString(category),
        duration = duration,
        seriesUri = seriesUri,
        seasonUri = seasonUri,
        episodeNumber = episodeNumber,
        seasonNumber = seasonNumber
    )
}

// TODO: If we want to save those objects in room for example, then map all the way around