package com.francis.core.datasource

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.francis.core.data.db.Movie
import com.francis.core.data.db.RemoteKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class RemoteMediatorDataSource @Inject constructor(
    private val remoteKeyDataSource: RemoteKeyDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource,
    private val moviesRemoteDataSource: MoviesRemoteDataSource
): RemoteMediator<Int, Movie>(){

    override suspend fun initialize(): InitializeAction {
        val count = withContext(Dispatchers.IO) { remoteKeyDataSource.getCount() }
        return if (count != null && count > 0) InitializeAction.SKIP_INITIAL_REFRESH
        else InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Movie>): MediatorResult {

        val loadKey = when (loadType) {
            LoadType.REFRESH -> null
            // In this example, you never need to prepend, since REFRESH
            // will always load the first page in the list. Immediately
            // return, reporting end of pagination.
            LoadType.PREPEND ->{
                Log.d(javaClass.simpleName, "load: prepend")
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            LoadType.APPEND -> {
                val item = withContext(Dispatchers.IO) { remoteKeyDataSource.getLastOrNull() }
                    ?: return MediatorResult.Success(endOfPaginationReached = true)

                Log.d(javaClass.simpleName, "load: $item")
                item.nextKey
            }
        }

        try {
            val response = moviesRemoteDataSource.fetchMovies(loadKey)
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!
                val isEndOfList = data.results.isNullOrEmpty()
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDataSource.nuke()
                    moviesLocalDataSource.nuke()
                }
                val prevKey = if (loadKey == null) null else loadKey - 1
                val nextKey = if (isEndOfList) null else (loadKey ?: 1) + 1
                val keys = data.results.map {
                    RemoteKey(movieId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                remoteKeyDataSource.insertAll(keys)
                moviesLocalDataSource.insertAll(data.results)
                return MediatorResult.Success(endOfPaginationReached = isEndOfList)
            } else return MediatorResult.Error(InvalidObjectException(response.message()))
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }
}