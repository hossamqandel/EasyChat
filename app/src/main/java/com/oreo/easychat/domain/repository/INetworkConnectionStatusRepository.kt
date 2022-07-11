package com.oreo.easychat.domain.repository

interface INetworkConnectionStatusRepository {

    suspend fun getNetworkConnectionStatus(isConnected: Boolean): Boolean
}