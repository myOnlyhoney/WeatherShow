package com.example.weathershow.ui.repo

import com.example.weathershow.BuildConfig
import com.example.weathershow.base.BaseResponse
import com.example.weathershow.bean.WeatherInfo
import com.example.weathershow.net.ServiceApi
import kotlinx.coroutines.flow.Flow

class WeatherRepoSitory {
    suspend fun getWeather(
        area: String
    ): WeatherInfo{
        return ServiceApi.service.getWeather(area,BuildConfig.APP_KEY)
    }
}