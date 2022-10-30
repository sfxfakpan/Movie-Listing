package com.francis.core.usecase

import com.francis.core.data.db.Movie
import com.francis.core.datasource.MoviesLocalDataSource
import com.francis.core.datasource.MoviesRemoteDataSource
import com.francis.core.datasource.RemoteKeyDataSource
import com.francis.core.datasource.RemoteMediatorDataSource
import com.francis.core.reposiitory.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response


@RunWith(JUnit4::class)
class FetchNUpdateTest {

    private val localDataSource: MoviesLocalDataSource = mockk()
    private val remoteKeyDataSource: RemoteKeyDataSource = mockk()
    private val remoteDataSource: MoviesRemoteDataSource = mockk()
    private lateinit var repository: MovieRepository

    @Before
    fun setUp() {
        val remoteMediator =
            RemoteMediatorDataSource(remoteKeyDataSource, localDataSource, remoteDataSource)
        repository = MovieRepository(localDataSource, remoteDataSource, remoteMediator)
    }

    @Test
    fun `fetch and update movie`() = runBlocking {
        val fetchNUpdate = FetchNUpdate(repository)

        coEvery {
            remoteDataSource.fetchMovieDetails(any())
        } returns Response.success(dummyData)

        coEvery {
            localDataSource.update(any())
        } returns Unit

        fetchNUpdate.invoke(1, this)

        coVerify(atLeast = 1) { repository.fetchMovieDetails(1) }

        coVerify(atLeast = 1) { repository.updateMovieDetails(dummyData) }

    }

    private val dummyData = Movie(100, "", "", "", 21, 2f, "", "", 1, "", listOf())

}