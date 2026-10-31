package com.example.pia_labdismov

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class VolleyCoachApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Timber logging initialization
        Timber.plant(Timber.DebugTree())
    }
}