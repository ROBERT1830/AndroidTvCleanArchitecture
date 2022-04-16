package com.robertconstantindinescu.mytvapp.util

sealed class DataSourceState<T> {
    class Empty<T>: DataSourceState<T>()
    class Loading<T>: DataSourceState<T>()
    data class Success<T>(
        val data: T,
        val message: String? = null
    ):DataSourceState<T>()
    data class Error<T>(
        val message: String? = null
    ): DataSourceState<T>()

}
