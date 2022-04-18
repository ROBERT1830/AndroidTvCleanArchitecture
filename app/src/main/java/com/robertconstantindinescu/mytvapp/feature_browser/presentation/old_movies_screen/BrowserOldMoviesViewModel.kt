package com.robertconstantindinescu.mytvapp.feature_browser.presentation.old_movies_screen

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.old_shared_movie.Category
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.old_shared_movie.OldMovieCategory
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
class BrowserOldMoviesViewModel @Inject constructor(
    private val useCases: BrowserUseCases
): ViewModel() {

    private val _state = MutableStateFlow<DataSourceState<List<Category>>>(DataSourceState.Empty())
    val state: StateFlow<DataSourceState<List<Category>>> = _state

    private val _singleUiEvent = Channel<SingleUiEvent>()
    val singleUiEvent = _singleUiEvent.receiveAsFlow()

    fun onEvent(event: BrowserOldMoviesEvent){
        when(event){
            is BrowserOldMoviesEvent.SearchOldMovies -> {
                executeJsonSearch(event.context)

            }
            is BrowserOldMoviesEvent.OnClickOldMovie -> {

            }
        }
    }



    private fun executeJsonSearch(context: Context) {

        viewModelScope.launch {
            with(_state){
                tryEmit(DataSourceState.Loading())
                val olMoviesList = useCases.searchOldMovieUseCase(context)
                val movieListCategorized = useCases.categorizeOldMovie(olMoviesList)
                tryEmit(DataSourceState.Success(movieListCategorized))
            }
        }
    }
}





























