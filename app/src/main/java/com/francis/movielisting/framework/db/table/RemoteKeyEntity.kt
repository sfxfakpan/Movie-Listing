package com.francis.movielisting.framework.db.table

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.francis.core.data.RemoteKey

@Entity(tableName = "remote_key")
data class RemoteKeyEntity(
    @PrimaryKey
    val movieId: Int,
    val prevKey: Int?,
    val nextKey: Int?
) {

    companion object {
        fun fromRemoteKey(key: RemoteKey) = RemoteKeyEntity(
            key.movieId, key.prevKey, key.nextKey
        )
    }

    fun toRemoteKey() = RemoteKey(
        movieId, prevKey, nextKey
    )
}