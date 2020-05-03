package com.sergeykulyk.corona_statistics.io.rest

import com.sergeykulyk.corona_statistics.BuildConfig


object Rest {

    private var apiFactory = ApiFactory()

    val covidService: Covid19Service by lazy {
        return@lazy apiFactory.buildRetrofit(BuildConfig.BASE_URL)
            .create(Covid19Service::class.java)
    }

    val novelCovidApi: NovelCovid19Service by lazy {
        return@lazy apiFactory.buildRetrofit(BuildConfig.NOVEL_COVID_API)
            .create(NovelCovid19Service::class.java)
    }

}