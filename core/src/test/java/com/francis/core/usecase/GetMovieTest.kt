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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetMovieTest {

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
    fun `get movie from local data source`() {
        val getMovie = GetMovie(repository)

        coEvery {
            localDataSource.get(any())
        } returns flow {
            emit(dummyData)
        }

        runBlocking {
            val result = getMovie.invoke(1)

            var actual: Movie? = null

            result.collect {
                actual = it
            }

            coVerify(atLeast = 1) { repository.getMovie(1) }

            assertEquals(dummyData, actual)
        }

    }

    private val dummyData = Movie(100, "", "", "", 21, 2f, "", "", 1, "", listOf())

}