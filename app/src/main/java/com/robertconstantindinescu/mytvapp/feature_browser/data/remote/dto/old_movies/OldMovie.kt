package com.robertconstantindinescu.mytvapp.feature_browser.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class OldMovie(
    val id: String,
    val name: String,
    val description: String,
    val uri: String,
    val videoUri: String,
    val thumbnailUri: String,
    @Json(name = "backgroundUri") val backgroundImageUri: String,
    val category: String,
    val videoType: VideoType,
    // The duration is specified in the ISO 8601 format as 'PT00H00M'. For more information on the
    // format, refer - https://en.wikipedia.org/wiki/ISO_8601.
    val duration: String = "PT00H00M",
    // The series, season and episode information is picked from the JSON feed that stores the
    // catalog. For consistency and proper formatting of the JSON, the fields for series, season and
    // episode data have been defined as empty strings for content types that are not TV Episodes.
    val seriesUri: String = "",
    val seasonUri: String = "",
    val episodeNumber: String = "",
    val seasonNumber: String = ""

):Parcelable


enum class VideoType {
    @Json(name = "clip")
    CLIP,

    @Json(name = "episode")
    EPISODE,

    @Json(name = "movie")
    MOVIE
}
