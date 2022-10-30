package com.francis.movielisting.framework.datasource

import com.francis.core.data.db.RemoteKey
import com.francis.core.data.db.dao.RemoteKeyDao
import com.francis.core.datasource.RemoteKeyDataSource
import javax.inject.Inject

class RemoteKeyRoomDataSource @Inject constructor(
    private val dao: RemoteKeyDao
) : RemoteKeyDataSource {
    override suspend fun insertAll(keys: List<RemoteKey>) {
        dao.insertAll(keys)
    }

    override suspend fun get(id: Int): RemoteKey? {
        return dao.getRemoteKey(id)
    }

    override suspend fun getLastOrNull(): RemoteKey? {
        return dao.getLastOrNull()
    }

    override suspend fun getCount(): Int? {
        return dao.getCount()
    }

    override suspend fun nuke() {
        dao.clearRemoteKeys()
    }
}