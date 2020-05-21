package com.corona.statistics.io.rest


import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiFactory {

    private var builder: OkHttpClient.Builder? = null

    private fun getClient(): OkHttpClient {
        if (builder == null) {

            builder = OkHttpClient.Builder()
            builder?.callTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            builder?.connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            builder?.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            builder?.readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)

            builder?.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {

                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                        .addHeader("Accept", "*/*")
                        .addHeader("Accept-Encoding", "application/json")
                        .addHeader("Connection", "keep-alive")
                        .method(original.method, original.body)

                    val request = requestBuilder
                        .cacheControl(CacheControl.Builder().noCache().build())
                        .build()

                    return chain.proceed(request)
                }
            })
        }

        builder?.addInterceptor(HttpLoggingInterceptor())

        return builder!!.build()
    }

    fun buildRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {

        private const val DEFAULT_TIMEOUT = 15
        const val CONNECT_TIMEOUT = DEFAULT_TIMEOUT
        const val WRITE_TIMEOUT = DEFAULT_TIMEOUT
        const val READ_TIMEOUT = DEFAULT_TIMEOUT

    }
}