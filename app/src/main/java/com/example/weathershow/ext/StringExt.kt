package com.example.weathershow.ext

import java.text.SimpleDateFormat
import java.util.*
val DAY = 24 * 60 * 60 * 1000L
fun String?.showWeek():String{
    this?.let {
        var fomrat = SimpleDateFormat("yyyy-MM-dd")
        var t1 = fomrat.format(Date(System.currentTimeMillis()))
        val today = fomrat.parse(t1).time
        val target = fomrat.parse(it).time

        var d = today - target
        // 只获取周几，和哪一天
        //return when (d) {
        // 2019-06-20 周几/哪一天
        return  when (d) {
            0L -> {
                "今天"
            }

            DAY -> {
                "昨天"
            }

            -DAY -> {
                "明天"
            }

            else -> {
                var date = SimpleDateFormat("yyyy-MM-dd").parse(it)
                val calendar = Calendar.getInstance()
                calendar.time = date
                getWeek(calendar.get(Calendar.DAY_OF_WEEK))
            }
        }
    }
   return ""
}
fun getWeek(week: Int):String{
    var w = ""

    when (week) {
        Calendar.SUNDAY -> {
            w = "周日"
        }
        Calendar.MONDAY -> {
            w = "周一"
        }
        Calendar.TUESDAY -> {
            w = "周二"
        }
        Calendar.WEDNESDAY -> {
            w = "周三"
        }
        Calendar.THURSDAY -> {
            w = "周四"
        }
        Calendar.FRIDAY -> {
            w = "周五"
        }
        Calendar.SATURDAY -> {
            w = "周六"
        }
    }
    return w
}
