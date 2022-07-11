package com.oreo.easychat.data.repository

import com.oreo.easychat.data.remote.INetworkDao
import com.oreo.easychat.domain.repository.INetworkConnectionStatusRepository
import javax.inject.Inject

class NetworkConnectionStatusRepositoryImpl @Inject constructor(
    private val iNetworkDao: INetworkDao
) : INetworkConnectionStatusRepository {

    override suspend fun getNetworkConnectionStatus(isConnected: Boolean): Boolean {
        return iNetworkDao.getNetworkConnectionStatus(isConnected)
    }
}