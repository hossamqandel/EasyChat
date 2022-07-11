package com.oreo.easychat.data.remote

import javax.inject.Inject


// Example implementation for INetworkDao
class INetworkDaoImpl @Inject constructor(): INetworkDao {

    override suspend fun getNetworkConnectionStatus(isConnected: Boolean): Boolean {
        return isConnected
    }
}