package com.oreo.easychat.ui.activity_screen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi

class ConnectivityReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context?, intent: Intent?) {
        mConnectivityReceiverListener?.onNetworkConnectionChanged(isConnectedOrConnecting(context!!))
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun isConnectedOrConnecting(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting

    }
    interface ConnectivityReceiverListener{
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }

    companion object {
        var mConnectivityReceiverListener: ConnectivityReceiverListener? = null
    }
}