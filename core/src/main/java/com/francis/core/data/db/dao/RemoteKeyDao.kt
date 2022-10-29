package com.francis.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.francis.core.data.db.RemoteKey

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKey>)

    @Query("SELECT * FROM remote_key WHERE movieId = :id")
    suspend fun getRemoteKey(id: Int): RemoteKey?

    @Query("DELETE FROM remote_key")
    suspend fun clearRemoteKeys()
}