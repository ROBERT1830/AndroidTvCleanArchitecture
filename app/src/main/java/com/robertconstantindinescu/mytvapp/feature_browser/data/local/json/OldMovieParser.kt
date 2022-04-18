package com.robertconstantindinescu.mytvapp.feature_browser.data.local.json

import android.provider.MediaStore
import com.robertconstantindinescu.mytvapp.feature_browser.data.remote.dto.old_movies.ApiResponse
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.OldMovie
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types


object OldMovieParser {

    fun loadVideosFromJson(jsonString: String): List<OldMovie>{
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(ApiResponse::class.java, OldMovie::class.java)
        val adapter = moshi.adapter<ApiResponse<OldMovie>>(listType)
        return adapter.fromJson(jsonString)!!.content
    }

    // TODO: Find video from json
}