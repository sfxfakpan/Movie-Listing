package com.francis.core.usecase

import com.francis.core.data.Cast
import com.francis.core.data.response.CreditsResponse
import com.francis.core.datasource.MoviesLocalDataSource
import com.francis.core.datasource.MoviesRemoteDataSource
import com.francis.core.datasource.RemoteKeyDataSource
import com.francis.core.datasource.RemoteMediatorDataSource
import com.francis.core.reposiitory.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class GetMovieCastTest {
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
    fun `fetch movie casts successfully`() {
        val fetchMovieCasts = GetMovieCast(repository)

        coEvery {
            remoteDataSource.fetchMovieCasts(any())
        } returns Response.success(CreditsResponse(dummyData.toList()))

        runBlocking {
            val result = fetchMovieCasts.invoke(2)

            var actual: List<Cast>? = null

            result.collect {
                actual = it
            }

            coVerify(atLeast = 1) { repository.fetchMovieCasts(2) }

            assertArrayEquals(dummyData, actual?.toTypedArray())
        }
    }

    @Test
    fun `fetch movie's casts failed`() {
        val fetchMovieVideos = GetMovieCast(repository)

        coEvery {
            remoteDataSource.fetchMovieVideos(any())
        } returns  Response.error(404, "An error occurred".toResponseBody())

        runBlocking {
            val result = fetchMovieVideos.invoke(2)

            var actual: List<Cast>? = null

            result.collect {
                actual = it
            }

            coVerify(atLeast = 1) { repository.fetchMovieCasts(2) }
            assertNull(actual)
        }
    }

    @Test
    fun `fetch movie's video returns null body`() {
        val fetchMovieVideos = GetMovieCast(repository)

        coEvery {
            remoteDataSource.fetchMovieVideos(any())
        } returns  Response.success(null)

        runBlocking {
            val result = fetchMovieVideos.invoke(2)

            var actual: List<Cast>? = null

            result.collect {
                actual = it
            }

            coVerify(atLeast = 1) { repository.fetchMovieCasts(2) }
            assertNull(actual)
        }
    }

    private val dummyData = Array(5) { Cast(21 * it,"", "") }

}