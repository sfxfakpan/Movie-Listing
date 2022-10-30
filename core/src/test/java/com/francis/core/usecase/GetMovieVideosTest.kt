package com.francis.core.usecase

import com.francis.core.data.Video
import com.francis.core.data.response.VideosResponse
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
class GetMovieVideosTest {
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
    fun `fetch movie's video links successfully`() {
        val fetchMovieVideos = GetMovieVideos(repository)

        coEvery {
            remoteDataSource.fetchMovieVideos(any())
        } returns Response.success(VideosResponse(dummyData.toList()))

        runBlocking {
            val result = fetchMovieVideos.invoke(2)

            var actual: List<Video>? = null

            result.collect {
                actual = it
            }

            coVerify(atLeast = 1) { repository.fetchMovieVideos(2) }

            assertArrayEquals(dummyData, actual?.toTypedArray())
        }
    }

    @Test
    fun `fetch movie's video links failed`() {
        val fetchMovieVideos = GetMovieVideos(repository)

        coEvery {
            remoteDataSource.fetchMovieVideos(any())
        } returns  Response.error(404, "An error occurred".toResponseBody())

        runBlocking {
            val result = fetchMovieVideos.invoke(2)

            var actual: List<Video>? = null

            result.collect {
                actual = it
            }

            coVerify(atLeast = 1) { repository.fetchMovieVideos(2) }
            assertNull(actual)
        }
    }

    @Test
    fun `fetch movie's video returns null body`() {
        val fetchMovieVideos = GetMovieVideos(repository)

        coEvery {
            remoteDataSource.fetchMovieVideos(any())
        } returns  Response.success(null)

        runBlocking {
            val result = fetchMovieVideos.invoke(2)

            var actual: List<Video>? = null

            result.collect {
                actual = it
            }

            coVerify(atLeast = 1) { repository.fetchMovieVideos(2) }
            assertNull(actual)
        }
    }

    private val dummyData = Array(5) { Video("video $it", "", "") }

}