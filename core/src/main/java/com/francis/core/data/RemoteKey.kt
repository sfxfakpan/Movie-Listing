package com.francis.core.data

import androidx.annotation.Keep

@Keep
data class RemoteKey(
    val movieId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)