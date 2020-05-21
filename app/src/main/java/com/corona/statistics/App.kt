package com.corona.statistics

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.corona.statistics.App.Companion.app

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