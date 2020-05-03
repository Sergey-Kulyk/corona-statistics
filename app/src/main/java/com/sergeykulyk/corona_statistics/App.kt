package com.sergeykulyk.corona_statistics

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.sergeykulyk.corona_statistics.App.Companion.app

class App : Application() {


    override fun onCreate() {
        super.onCreate()

        app = this
    }

    companion object {
        lateinit var app: App
    }
}

fun getAppContext(): Context = app
fun getResources(): Resources = getAppContext().resources