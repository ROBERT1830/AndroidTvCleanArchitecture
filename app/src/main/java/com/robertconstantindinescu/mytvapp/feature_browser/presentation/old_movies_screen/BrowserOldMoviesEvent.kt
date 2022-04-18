package com.robertconstantindinescu.mytvapp.feature_browser.presentation.old_movies_screen

import android.content.Context
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.old_shared_movie.SharedOldMovie

sealed class BrowserOldMoviesEvent{
    data class SearchOldMovies(val context: Context): BrowserOldMoviesEvent()
    //data class DisplayData(val browserFragment: BrowseSupportFragment): BrowserOldMoviesEvent()
    // TODO: maybe only the videoUri and title are needed.
    data class OnClickOldMovie(val sharedOldMovie: SharedOldMovie): BrowserOldMoviesEvent()

}
