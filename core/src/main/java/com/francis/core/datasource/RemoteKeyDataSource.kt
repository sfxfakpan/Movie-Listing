package com.francis.core.datasource

import com.francis.core.data.RemoteKey

interface RemoteKeyDataSource {

    suspend fun insertAll(keys: List<RemoteKey>)

    suspend fun get(id: Int): RemoteKey?

    suspend fun nuke()
}