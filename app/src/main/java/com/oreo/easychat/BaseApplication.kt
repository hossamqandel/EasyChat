package com.oreo.easychat

import android.app.Application
import com.oreo.easychat.core.MySharedPref
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MySharedPref.init(this)
    }

}