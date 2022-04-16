package com.robertconstantindinescu.mytvapp.feature_browser.presentation.home_screen

import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.SharedMovie

data class BrowserScreenState(
    val movieList: List<SharedMovie> = listOf()
    // TODO: could be added more
)
