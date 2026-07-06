package com.mupel.clock.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MupelClockApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize app-level configurations if needed
    }
}
