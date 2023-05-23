package com.example.weathershow.ui.adapter

import android.content.Context
import android.icu.text.CaseMap
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.QuickViewHolder
import com.example.weathershow.R
import com.example.weathershow.bean.Cast
import com.example.weathershow.ext.showWeek

class CityOtherTemperatureItemAdapter(
    datas: List<Cast>?,
    val layoutId: Int = R.layout.item_city_data
) : BaseQuickAdapter<Cast, QuickViewHolder>() {
    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: Cast?) {
        item?.let {
            Log.d("test", "onBindViewHolder: ")
            holder.getView<TextView>(R.id.tv_city_time).text = it.date?.showWeek()
            holder.getView<TextView>(R.id.tv_other_temperature).text =
                it.daytemp.plus("℃").plus("/").plus(it.nighttemp).plus("℃")
        }

    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        Log.d("test", "onCreateViewHolder: ")
        return QuickViewHolder(layoutId, parent)
    }
}