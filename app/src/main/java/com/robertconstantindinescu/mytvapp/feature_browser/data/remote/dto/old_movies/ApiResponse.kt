package com.robertconstantindinescu.mytvapp.feature_browser.data.remote.dto.old_movies

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ApiResponse<T>(val content: List<T>, val metadata: Map<String, String>?)
