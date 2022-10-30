package com.francis.core.data.db

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.francis.core.data.Genre
import com.francis.core.data.db.converter.GenreConverter
import com.google.gson.annotations.SerializedName

@Keep
@Entity(tableName = "movies")
data class Movie(
    @SerializedName("id")
    @PrimaryKey
    var id: Int,

    @SerializedName("poster_path")
    var posterPath: String?,

    @SerializedName("backdrop_path")
    var backdropPath: String?,

    @SerializedName("title")
    var title: String,

    @SerializedName("vote_count")
    var voteCount: Int,

    @SerializedName("vote_average")
    var voteAverage: Float,

    @SerializedName("original_language")
    var originalLanguage: String? = "",

    @SerializedName("release_date")
    var releaseDate: String?,

    @SerializedName("runtime")
    var runtime: Int?,

    @SerializedName("overview")
    var overview: String?,

    @SerializedName("genres")
    @TypeConverters(GenreConverter::class)
    var genres: List<Genre>?
)