package com.example.weathershow.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkHttpClientManager {
    val mClient: OkHttpClient by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        buildClient()
    }

    private fun buildClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().apply {
            addInterceptor(logging)
            followSslRedirects(true)
        }.build()
    }

}