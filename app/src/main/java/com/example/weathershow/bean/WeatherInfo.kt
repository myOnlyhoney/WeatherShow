package com.example.weathershow.bean

data class WeatherInfo(
    var count: String?,
    var forecasts: List<Forecast>?,
    var info: String?,
    var infocode: String?,
    var status: String?
)

data class Forecast(
    val adcode: String,
    val casts: List<Cast>,
    val city: String,
    val province: String,
    val reporttime: String
)

data class Cast(
    val date: String,
    val daypower: String,
    val daytemp: String,
    val daytemp_float: String,
    val dayweather: String,
    val daywind: String,
    val nightpower: String,
    val nighttemp: String,
    val nighttemp_float: String,
    val nightweather: String,
    val nightwind: String,
    val week: String
){
fun getWeather():String=if (dayweather.equals(nightweather))dayweather else dayweather.plus("转").plus(nightweather)
fun getTemperature():String= daytemp.plus("℃").plus("/").plus(nighttemp).plus("℃")
fun getWind():String= if (daywind.equals(nightwind))daywind else daywind.plus("转").plus(nightwind)
}