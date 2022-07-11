package com.oreo.easychat.di

import com.oreo.easychat.data.remote.INetworkDao
import com.oreo.easychat.data.remote.INetworkDaoImpl
import com.oreo.easychat.data.repository.NetworkConnectionStatusRepositoryImpl
import com.oreo.easychat.domain.repository.INetworkConnectionStatusRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface IAppModule {

    @Binds
    fun bindNetworkDao(iNetworkDaoImpl: INetworkDaoImpl): INetworkDao

    @Binds
    fun bindNetworkConnectionStatusRepo(networkConnectionStatusRepoImpl: NetworkConnectionStatusRepositoryImpl): INetworkConnectionStatusRepository
}