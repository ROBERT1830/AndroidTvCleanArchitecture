package com.robertconstantindinescu.mytvapp.feature_browser.domain.model.old_shared_movie

import com.robertconstantindinescu.mytvapp.feature_browser.domain.util.Constants.BEVERLY_HILLBILLIES
import com.robertconstantindinescu.mytvapp.feature_browser.domain.util.Constants.CHARLIE_CHAPLIN_MOVIES
import com.robertconstantindinescu.mytvapp.feature_browser.domain.util.Constants.MISC_CLIPS
import com.robertconstantindinescu.mytvapp.feature_browser.domain.util.Constants.SUPERCHARGED_CLIPS

sealed class OldMovieCategory(val category: String){
    object SuperChargedClip: OldMovieCategory("Supercharged Clips")
    object MiscClip: OldMovieCategory("Misc Clips")
    object BeverlyHillBillie: OldMovieCategory("Beverly Hillbillies")
    object CharlieChaplinMovie: OldMovieCategory("Charlie Chaplin Movies")


    companion object{
        fun fromString(category: String): OldMovieCategory{

            return when(category){
                SUPERCHARGED_CLIPS -> SuperChargedClip
                MISC_CLIPS -> MiscClip
                BEVERLY_HILLBILLIES -> BeverlyHillBillie
                CHARLIE_CHAPLIN_MOVIES -> CharlieChaplinMovie
                else -> MiscClip
            }
        }
    }

}
