package com.francis.core.datasource

import com.francis.core.data.db.RemoteKey

interface RemoteKeyDataSource {

    suspend fun insertAll(keys: List<RemoteKey>)

    suspend fun get(id: Int): RemoteKey?

    suspend fun getLastOrNull(): RemoteKey?

    suspend fun getCount(): Int?

    suspend fun nuke()
}