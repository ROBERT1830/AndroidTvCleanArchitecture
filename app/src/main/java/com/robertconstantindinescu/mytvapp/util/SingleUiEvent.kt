package com.robertconstantindinescu.mytvapp.util

sealed class SingleUiEvent() {

    object NavigateSucess: SingleUiEvent()

    object NavigateToDetails: SingleUiEvent()
    object NavigateToOldMovies: SingleUiEvent()
    object NavigationUp: SingleUiEvent()
    //data class ShowSnackBar(val message: UiText):SingleUiEvent()
}