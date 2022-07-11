package com.oreo.easychat.di

import com.oreo.easychat.data.remote.INetworkDao
import com.oreo.easychat.data.repository.NetworkConnectionStatusRepositoryImpl
import com.oreo.easychat.domain.repository.INetworkConnectionStatusRepository
import com.oreo.easychat.domain.use_case.GetConnectionStatusUseCase
import com.oreo.easychat.ui.activity_screen.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideGetConnectionStatusUseCase(iNetworkConnectionStatusRepository: INetworkConnectionStatusRepository): GetConnectionStatusUseCase {
        return GetConnectionStatusUseCase(iNetworkConnectionStatusRepository)
    }

    @Provides
    @Singleton
    fun provideNetworkConnectionStatusRepository(iNetworkDao: INetworkDao): INetworkConnectionStatusRepository {
        return NetworkConnectionStatusRepositoryImpl(iNetworkDao)
    }


    @Provides
    @Singleton
    fun provideMainViewModel(getConnectionStatusUseCase: GetConnectionStatusUseCase): MainViewModel {
        return MainViewModel(getConnectionStatusUseCase)
    }

}