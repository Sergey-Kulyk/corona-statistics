package com.sergeykulyk.corona_statistics.io.rest

import com.sergeykulyk.corona_statistics.BuildConfig


object Rest {

    private var apiFactory = ApiFactory()

    val covid19: Covid19Service by lazy {
        return@lazy apiFactory.buildRetrofit(BuildConfig.BASE_URL)
            .create(Covid19Service::class.java)
    }

}