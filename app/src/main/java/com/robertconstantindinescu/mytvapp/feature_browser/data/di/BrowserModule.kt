package com.robertconstantindinescu.mytvapp.feature_browser.data.di

import android.app.Application
import androidx.room.Room
import com.robertconstantindinescu.mytvapp.feature_browser.data.local.MovieDatabase
import com.robertconstantindinescu.mytvapp.feature_browser.data.remote.MovieApi
import com.robertconstantindinescu.mytvapp.feature_browser.data.repository.MovieRepoImpl
import com.robertconstantindinescu.mytvapp.feature_browser.data.util.Constants.MOVIE_BASE_URL
import com.robertconstantindinescu.mytvapp.feature_browser.domain.repository.MovieRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BrowserModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(okHttpClient: OkHttpClient): MovieApi {
        return Retrofit.Builder()
            .baseUrl(MOVIE_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(application: Application): MovieDatabase{
        return Room.databaseBuilder(
            application,
            MovieDatabase::class.java,
            "movie_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        api: MovieApi,
        db:MovieDatabase
    ): MovieRepo {
        return MovieRepoImpl(
            api = api,
            dao = db.dao
        )

    }
}