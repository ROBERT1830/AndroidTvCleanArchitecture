package com.robertconstantindinescu.mytvapp.feature_browser.domain.model

import com.robertconstantindinescu.mytvapp.feature_browser.domain.util.Constants.ACTION
import com.robertconstantindinescu.mytvapp.feature_browser.domain.util.Constants.ADVENTURE
import com.robertconstantindinescu.mytvapp.feature_browser.domain.util.Constants.ANIMATION
import com.robertconstantindinescu.mytvapp.feature_browser.domain.util.Constants.COMEDY
import com.robertconstantindinescu.mytvapp.feature_browser.domain.util.Constants.CRIME
import com.robertconstantindinescu.mytvapp.feature_browser.domain.util.Constants.DRAMA
import com.robertconstantindinescu.mytvapp.feature_browser.domain.util.Constants.FAMILY
import com.robertconstantindinescu.mytvapp.feature_browser.domain.util.Constants.FANTASY
import com.robertconstantindinescu.mytvapp.feature_browser.domain.util.Constants.SCI_FI
import com.robertconstantindinescu.mytvapp.feature_browser.domain.util.Constants.WESTERN

sealed class Genre(val type: String){
    object Action: Genre(ACTION)
    object Drama: Genre(DRAMA)
    object SciFi: Genre(SCI_FI)
    object Animation: Genre(ANIMATION)
    object Adventure: Genre(ADVENTURE)
    object Comedy: Genre(COMEDY)
    object Western: Genre(WESTERN)
    object Family: Genre(FAMILY)
    object Crime: Genre(CRIME)
    object Fantasy: Genre(FANTASY)

    companion object{
        fun fromString(type: String): Genre{
            return when(type){
                ACTION -> {Action}
                DRAMA ->{Drama}
                SCI_FI -> {SciFi}
                ANIMATION -> {Animation}
                ADVENTURE -> {Adventure}
                COMEDY -> {Comedy}
                WESTERN -> {Western}
                FAMILY -> {Family}
                CRIME -> {Crime}
                FANTASY -> {Fantasy}
                else -> Action

            }
        }
    }


}
