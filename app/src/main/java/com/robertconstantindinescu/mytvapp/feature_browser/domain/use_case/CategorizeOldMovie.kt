package com.robertconstantindinescu.mytvapp.feature_browser.domain.use_case

import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.old_shared_movie.Category
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.old_shared_movie.OldMovieCategory
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.old_shared_movie.SharedOldMovie

class CategorizeOldMovie {

    operator fun invoke(movieList: List<SharedOldMovie>): List<Category>{

        val categorySet = mutableSetOf<OldMovieCategory>()
        for (movie in movieList){
            categorySet.add(movie.oldMovieCategory)
        }
        val feedItems = mutableListOf<Category>()
        categorySet.forEachIndexed { index, oldMovieCategory ->
            val categoryId = index.toLong()
            movieList.filter {
                it.oldMovieCategory == oldMovieCategory
            }.map {
                it.copy().apply {
                    this.id = categoryId.toString()
                }
            }.apply {
                feedItems.add(
                    Category(
                        id = categoryId,
                        type = oldMovieCategory.category,
                        movie = this
                    )
                )
            }
        }
        return feedItems

    }
}