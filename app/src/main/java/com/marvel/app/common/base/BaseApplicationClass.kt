package com.marvel.app.common.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}