package com.francis.core.data.response

import androidx.annotation.Keep
import com.francis.core.data.db.Movie
import com.google.gson.annotations.SerializedName

@Keep
data class MoviesResponse(
    @SerializedName("page")
    override var page: Int,

    @SerializedName("results")
    override var results: List<Movie>
) : BasePageListResponse<Movie>