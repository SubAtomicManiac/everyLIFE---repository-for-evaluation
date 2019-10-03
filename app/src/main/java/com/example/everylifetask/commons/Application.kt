package com.example.everylifetask.commons

import android.app.Application
import android.content.Context

class Application : Application() {
    companion object {
        @SuppressWarnings("all")
        var context: Context? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}