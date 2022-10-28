package com.francis.movielisting.framework.db.table

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.francis.core.data.Genre
import com.francis.core.data.Movie
import com.francis.movielisting.framework.db.converter.GenreConverter

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Int,

    val posterPath: String?,

    val backdropPath: String?,

    val title: String,

    val voteCount: Int,

    val voteAverage: Float,

    val originalLanguage: String? = "",

    val releaseDate: String?,

    val runtime: Int?,

    val overview: String?,

    @TypeConverters(GenreConverter::class)
    val genres: List<Genre>?
) {

    companion object {
        fun Movie.toDbDomain() = MovieEntity(
            id,
            posterPath,
            backdropPath,
            title,
            voteCount,
            voteAverage,
            originalLanguage,
            releaseDate,
            runtime,
            overview,
            genres
        )
    }

    fun MovieEntity.toDomain() = Movie(
        id,
        posterPath,
        backdropPath,
        title,
        voteCount,
        voteAverage,
        originalLanguage,
        releaseDate,
        runtime,
        overview,
        genres
    )

}