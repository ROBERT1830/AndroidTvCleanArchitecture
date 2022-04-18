package com.robertconstantindinescu.mytvapp.feature_browser.domain.model.old_shared_movie

data class SharedOldMovie(

    var id: String,
    val name: String,
    val description: String,
    val uri: String,
    val videoUri: String,
    val thumbnailUri: String,
    val oldMovieCategory: OldMovieCategory,
    val duration: String = "PT00H00M",
    // The series, season and episode information is picked from the JSON feed that stores the
    // catalog. For consistency and proper formatting of the JSON, the fields for series, season and
    // episode data have been defined as empty strings for content types that are not TV Episodes.
    val seriesUri: String = "",
    val seasonUri: String = "",
    val episodeNumber: String = "",
    val seasonNumber: String = ""
)


