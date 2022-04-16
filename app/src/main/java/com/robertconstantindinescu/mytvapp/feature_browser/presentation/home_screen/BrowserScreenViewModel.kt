package com.robertconstantindinescu.mytvapp.feature_browser.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.Category
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.SharedMovie
import com.robertconstantindinescu.mytvapp.feature_browser.domain.use_case.BrowserUseCases
import com.robertconstantindinescu.mytvapp.util.DataSourceState
import com.robertconstantindinescu.mytvapp.util.SingleUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrowserScreenViewModel @Inject constructor(
    private val useCases: BrowserUseCases
) : ViewModel() {

    private val _state = MutableStateFlow<DataSourceState<List<Category>>>(DataSourceState.Empty())
    val state: StateFlow<DataSourceState<List<Category>>> = _state

    private val _singleUiEvent = Channel<SingleUiEvent>()
    val singleUiEvent = _singleUiEvent.receiveAsFlow()

    fun onEvent(event: BrowserScreenEvent) {
        when (event) {
            is BrowserScreenEvent.SearchMovies -> {
                executeSearch()
            }
            is BrowserScreenEvent.DetailNavigate -> {

            }
        }
    }

    /**
     *
     * tryEmit -> is not a suspending function and needs a buffer where to store emitted value
     * for the subscribers.
     * emit -> is a suspending function and waits till subscribers are ready to collect data.
     */
    private fun executeSearch() {
        viewModelScope.launch {
            with(_state) {
                tryEmit(DataSourceState.Loading())
                val categoryList = getCategoryList(useCases.searchMovieUseCase.invoke())
                tryEmit(DataSourceState.Success(categoryList))
            }
        }
    }

    private fun getCategoryList(response: Result<List<SharedMovie>>): List<Category> {

        var categoryList = listOf<Category>()

        when {
            response.isSuccess -> {
                response.map {
                    categoryList = it.categorize()
                }
            }
            //emptyList
            response.isFailure -> {
                listOf<Category>()
            }
        }
        return categoryList
    }
}

private fun List<SharedMovie>.categorize(): List<Category> {
    val genreSet = mutableSetOf<String>()
    for (movie in this) {
        for (genre in movie.genre) {
            genreSet.add(genre)
        }
    }
    val feedItems = mutableListOf<Category>()
    genreSet.forEachIndexed { index, genre ->
        val categoryId = index.toLong()
        this.filter {
            it.genre.contains(genre)
        }.map {
            it.copy().apply {
                this.id = categoryId
            }
        }.run {
            feedItems.add(
                Category(
                    id = categoryId,
                    genre = genre,
                    movies = this
                )
            )
        }

    }
    return feedItems


}






















































