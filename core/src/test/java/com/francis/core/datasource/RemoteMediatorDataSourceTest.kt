package com.francis.core.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import com.francis.core.data.db.Movie
import com.francis.core.data.response.MoviesResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@ExperimentalPagingApi
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class RemoteMediatorDataSourceTest {

    private val localDataSource: MoviesLocalDataSource = mockk()
    private val remoteKeyDataSource: RemoteKeyDataSource = mockk()
    private val remoteDataSource: MoviesRemoteDataSource = mockk()

    private lateinit var remoteMediator: RemoteMediatorDataSource
    private var result = mutableListOf<Movie>()

    @Before
    fun setUp() {
        remoteMediator =
            RemoteMediatorDataSource(remoteKeyDataSource, localDataSource, remoteDataSource)
    }

    @Test
    fun `LoadType Refresh loads first page of items if available`() {
        val pagingState = PagingState<Int, Movie>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )

        coEvery {
            remoteDataSource.fetchMovies(any())
        } returns Response.success(MoviesResponse(1, dummySource))

        coEvery { remoteKeyDataSource.nuke() } returns Unit
        coEvery { localDataSource.nuke() } returns Unit
        coEvery { remoteKeyDataSource.insertAll(any()) } returns Unit
        coEvery { localDataSource.insertAll(any()) } answers {
            result.addAll(firstArg())
        }

        runBlocking {
            remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(dummySource.size == result.size)
            assertArrayEquals(dummySource.toTypedArray(), result.toTypedArray())
        }
    }

    private val dummySource = Array(10) {
        Movie(100 + (3 * it), "", "", "", it + 21, it*2f, "", "", 1 * it, "", listOf())
    }.toList()
}