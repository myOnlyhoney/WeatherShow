package com.example.weathershow.net

import com.example.weathershow.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceApi {
    val service :CoroutineService by lazy (LazyThreadSafetyMode.SYNCHRONIZED){
        build()
    }
    private fun build():CoroutineService{
        val retrofit = Retrofit.Builder().apply {
            baseUrl(BuildConfig.BASE_URL)
            client(OkHttpClientManager.mClient)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
        return  retrofit.create(CoroutineService::class.java)

    }
}