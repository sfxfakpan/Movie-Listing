package com.francis.movielisting.framework.di

import android.content.Context
import com.francis.core.data.db.dao.MovieDao
import com.francis.core.data.db.dao.RemoteKeyDao
import com.francis.movielisting.framework.db.AppDatabase
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