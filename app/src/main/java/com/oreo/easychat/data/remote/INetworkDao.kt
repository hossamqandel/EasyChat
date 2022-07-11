package com.oreo.easychat.data.remote

interface INetworkDao {

    suspend fun getNetworkConnectionStatus(isConnected: Boolean): Boolean
}