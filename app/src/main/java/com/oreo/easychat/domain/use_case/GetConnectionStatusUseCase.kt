package com.oreo.easychat.domain.use_case

import com.oreo.easychat.core.Const
import com.oreo.easychat.core.Resource
import com.oreo.easychat.domain.repository.INetworkConnectionStatusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetConnectionStatusUseCase @Inject constructor(
    private val iNetworkConnectionStatusRepository: INetworkConnectionStatusRepository
) {
    operator fun invoke(isConnected: Boolean): Flow<Resource<Boolean>> = flow {
        val result = iNetworkConnectionStatusRepository.getNetworkConnectionStatus(isConnected)
        if (result){
            emit(Resource.Success(result))
        } else {
            emit(Resource.Error(data = false, message = Const.OFFLINE_MODE_MESSAGE))
        }
    }
}