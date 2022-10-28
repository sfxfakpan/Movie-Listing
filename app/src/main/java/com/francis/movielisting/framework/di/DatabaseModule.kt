package com.francis.movielisting.framework.di

import android.content.Context
import com.francis.movielisting.framework.db.AppDatabase
import com.francis.movielisting.framework.db.dao.MovieDao
import com.francis.movielisting.framework.db.dao.RemoteKeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getInstance(context)

    @Provides
    fun provideMoviesDao(db: AppDatabase): MovieDao = db.movieDao()

    @Provides
    fun providesRemoteKeyDao(db: AppDatabase): RemoteKeyDao = db.remoteKeyDao()

}