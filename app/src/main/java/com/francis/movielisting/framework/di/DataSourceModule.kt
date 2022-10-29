package com.francis.movielisting.framework.di

import com.francis.core.datasource.MoviesLocalDataSource
import com.francis.core.datasource.MoviesRemoteDataSource
import com.francis.core.datasource.RemoteKeyDataSource
import com.francis.movielisting.framework.datasource.MovieRoomDataSource
import com.francis.movielisting.framework.datasource.RemoteKeyRoomDataSource
import com.francis.movielisting.framework.datasource.RetrofitDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DataSourceModule {

    @Binds
    fun bindsLocalDataSource(dataSource: MovieRoomDataSource): MoviesLocalDataSource

    @Binds
    fun bindsRemoteDataSource(dataSource: RetrofitDataSource): MoviesRemoteDataSource

    @Binds
    fun bindsRemoteKeyDataSource(dataSource: RemoteKeyRoomDataSource): RemoteKeyDataSource
}