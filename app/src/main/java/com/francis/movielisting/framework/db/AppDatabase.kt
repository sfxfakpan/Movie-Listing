package com.francis.movielisting.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.francis.core.data.db.Movie
import com.francis.core.data.db.RemoteKey
import com.francis.core.data.db.converter.GenreConverter
import com.francis.core.data.db.dao.MovieDao
import com.francis.core.data.db.dao.RemoteKeyDao

@Database(
    entities = [Movie::class, RemoteKey::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(GenreConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun remoteKeyDao(): RemoteKeyDao

    companion object {
        private const val DATABASE_NAME = "movies.db"

        private var instance: AppDatabase? = null

        private fun create(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()


        fun getInstance(context: Context): AppDatabase =
            (instance ?: create(context)).also { instance = it }
    }
}