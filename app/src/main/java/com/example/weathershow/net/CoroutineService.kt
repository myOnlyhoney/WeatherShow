package com.example.weathershow.net

import com.example.weathershow.base.BaseResponse
import com.example.weathershow.bean.WeatherInfo
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface CoroutineService {
    /**
     * 获取天气数据
     * @city 城市代码
     * @key 用户key
     */
    @GET("weatherInfo")
    suspend fun getWeather(
        @Query("city") city: String,
        @Query("key")key:String,
        @Query("extensions")extensions:String="all"
    ): WeatherInfo
}