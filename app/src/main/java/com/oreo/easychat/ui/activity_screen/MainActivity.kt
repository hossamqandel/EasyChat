package com.oreo.easychat.ui.activity_screen

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.oreo.easychat.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    private val TAG = "MainActivity"
    private val mFirestoreDatabase = Firebase.firestore.collection("users")
    private val mReceiver by lazy { ConnectivityReceiver() }
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver(mReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        getNetworkConnectionStatus()


    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.mConnectivityReceiverListener = this
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        Log.e(TAG, "onNetworkConnectionChanged: $isConnected")
        viewModel.onNetworkState(isConnected)
    }


    private fun updatePersonConnectionStatus(isConnected: Boolean) = CoroutineScope(Dispatchers.IO).launch {
            val newMap = mutableMapOf<String, Any>()
            newMap["isOnline"] = isConnected

            val connectionQuery = mFirestoreDatabase
                .whereEqualTo("name", "hossam")
                .whereEqualTo("phone", "07775000")
                .get()
                .await()

            if (connectionQuery.documents.isNotEmpty()) {
                for (document in connectionQuery) {
                    try {
                        mFirestoreDatabase.document(document.id).set(
                            newMap,
                            SetOptions.merge()
                        )
                    } catch (e: Exception) {
                        Log.e(TAG, "updatePersonConnectionStatus: ${e.message}")
                    }
                }
            } else {

            }
        }


    private fun addPerson() {
        val map = mutableMapOf<String, Any>()
        map["name"] = "mazen"
        map["phone"] = "1997"
        map["isOnline"] = true
        CoroutineScope(Dispatchers.IO).launch {
            mFirestoreDatabase.add(map).await()
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(mReceiver)
    }

    private fun getNetworkConnectionStatus(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.networkChannel.collectLatest { networkConnectionState ->
                    updatePersonConnectionStatus(networkConnectionState.isConnected)
                    Log.e(TAG, "getNetworkConnectionStatus: ${networkConnectionState.message}", )
                }
            }
        }
    }
}
