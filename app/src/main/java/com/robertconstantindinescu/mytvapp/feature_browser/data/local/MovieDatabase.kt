package com.robertconstantindinescu.mytvapp.feature_browser.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.robertconstantindinescu.mytvapp.feature_browser.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
@TypeConverters(DatabaseConverter::class)
 abstract class MovieDatabase: RoomDatabase() {
     abstract val dao: MovieDao
}