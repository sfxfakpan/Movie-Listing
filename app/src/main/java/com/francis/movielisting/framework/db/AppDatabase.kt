package com.francis.movielisting.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.francis.movielisting.framework.db.dao.MovieDao
import com.francis.movielisting.framework.db.dao.RemoteKeyDao
import com.francis.movielisting.framework.db.table.MovieEntity

@Database(entities = [MovieEntity::class], exportSchema = false, version = 1)
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