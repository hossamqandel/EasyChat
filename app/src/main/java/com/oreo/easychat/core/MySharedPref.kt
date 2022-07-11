package com.oreo.easychat.core

import android.content.Context
import android.content.SharedPreferences

object MySharedPref {

    private var mAppContext: Context? = null

    private val SHARED_PREFERENCES_NAME = "user data"

    fun init(context: Context?) {
        mAppContext = context
    }

    private fun getSharedPreferences(): SharedPreferences? {
        return mAppContext?.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }


}