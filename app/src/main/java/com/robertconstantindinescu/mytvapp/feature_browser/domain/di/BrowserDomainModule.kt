package com.robertconstantindinescu.mytvapp.feature_browser.domain.di

import com.robertconstantindinescu.mytvapp.feature_browser.domain.repository.MovieRepo
import com.robertconstantindinescu.mytvapp.feature_browser.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object BrowserDomainModule {
    @ViewModelScoped
    @Provides
    fun provideBrowserUseCases(
        repository: MovieRepo
    ) =  BrowserUseCases(
        searchNewMovieUseCase = SearchNewMovieUseCase(repository),
        // TODO: When those will be implemented, will need repository
        getMoviesUseCase = GetMoviesUseCase(),
        getMovieUseCase = GetMovieUseCase(),
        trackMovieUseCase = TrackMovieUseCase(),
        searchOldMovieUseCase = SearchOldMovieUseCase(repository),
        categorizeOldMovie = CategorizeOldMovie()
    )
}