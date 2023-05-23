package com.example.weathershow.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weathershow.bean.WeatherInfo
import com.example.weathershow.ext.requestMain
import com.example.weathershow.ui.repo.WeatherRepoSitory
import kotlinx.coroutines.flow.MutableStateFlow

class WeatherViewModel(private val repo: WeatherRepoSitory) : ViewModel() {
    var currentPos = 0
    val cityList = mutableListOf("北京", "上海", "广州", "深圳", "苏州", "沈阳")
    val cityCodeList = mutableListOf("110000", "310000", "440100", "440300", "320500", "210100")
    val weatherInfo: MutableStateFlow<WeatherInfo> =
        MutableStateFlow(WeatherInfo(null, null, null, null, null))

    fun getWeather(code: String) {
        requestMain {
            weatherInfo.value = repo.getWeather(code)
            Log.d("test", "getWeather: ")
        }
    }

    fun changeCity(pos: Int) {
        currentPos = pos
        getWeather(cityCodeList[currentPos])
    }
}