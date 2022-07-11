package com.oreo.easychat.ui.activity_screen

import androidx.lifecycle.ViewModel
import com.oreo.easychat.core.Resource
import com.oreo.easychat.domain.use_case.GetConnectionStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getConnectionStatusUseCase: GetConnectionStatusUseCase
) : ViewModel() {

    private val _networkChannel = Channel<NetworkConnectionState>()
    val networkChannel = _networkChannel.receiveAsFlow()

    fun onNetworkState(isConnected: Boolean) {
        getConnectionStatusUseCase.invoke(isConnected).onEach { Resource ->
            when (Resource) {
                is Resource.Success -> {
                    _networkChannel.send(NetworkConnectionState(isConnected = Resource.data!!))
                }

                is Resource.Error -> {
                    _networkChannel.send(NetworkConnectionState(
                            isConnected = Resource.data!!,
                            message = "${Resource.message}"
                        )
                    )
                }
            }
        }
    }
}