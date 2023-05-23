package com.example.weathershow.ui.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.weathershow.R
import com.example.weathershow.base.viewmodel.ViewModelUtils
import com.example.weathershow.base.viewmodel.viewModels
import com.example.weathershow.databinding.ActivityMainBinding
import com.example.weathershow.ui.adapter.CityOtherTemperatureItemAdapter
import com.example.weathershow.ui.viewmodel.WeatherViewModel
import com.example.weathershow.util.loadGif
import com.example.weathershow.util.loadImage
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<WeatherViewModel> {
        ViewModelUtils.provideMainViewModelFactory()
    }
    private val cityAdapter: CityOtherTemperatureItemAdapter by lazy {
        CityOtherTemperatureItemAdapter(null, R.layout.item_city_data)
    }
    private var firstX: Float = 0f
    private var firstY: Float = 0f
    private var secondX: Float = 0f
    private var secondY: Float = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTranslucentStatus()
        val databding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        databding.rvCity?.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = cityAdapter
        }
        lifecycleScope.launchWhenCreated {
            viewModel.getWeather(viewModel.cityCodeList[viewModel.currentPos])
            viewModel.weatherInfo.collect {
                Log.d("test", "onCreate:${viewModel.cityList}-${viewModel.currentPos} ")
                databding.city = viewModel.cityList[viewModel.currentPos]
                Log.d("test", "onCreate:${it.toString()} ")
                it.forecasts?.get(0)?.let {
                    it.casts.let { list ->
                        databding.weatherInfo = list[1]
                        cityAdapter.submitList(list)
                        list[1].dayweather?.let {
                            when (it) {
                                "晴" -> {
                                    databding.ivBg.loadImage("https://img1.baidu.com/it/u=2816039783,1800187988&fm=253&fmt=auto&app=138&f=JPEG?w=670&h=432")
                                }
                                "多云" -> {
                                    databding.ivBg.loadImage("https://img95.699pic.com/xsj/00/e1/9j.jpg%21/fw/700/watermark/url/L3hzai93YXRlcl9kZXRhaWwyLnBuZw/align/southeast")
                                }
                                "阴天" -> {
                                    databding.ivBg.loadImage(
                                        "https://hbimg.huaban.com/fde6e3b84adcef69b615ef64e69b4514c874322afd5ec-tNc34Z_fw658"
                                    )
                                }
                                "有雨" -> {
                                    databding.ivBg.loadGif("https://img27.51tietu.net/pic/2017-011419/20170114190024dcze41uwshp98259.gif")
                                }
                                "雪" -> {

                                    databding.ivBg.loadGif("https://hbimg.b0.upaiyun.com/8c5e1b30d263c6e546ae5a5444eb18dfbb2ac5eec8308-n2RgDZ_fw658")
                                }
                                else -> {
                                    databding.ivBg.loadImage("https://5b0988e595225.cdn.sohucs.com/images/20181201/058013c039b144af90f24fd73b597715.gif")
                                }
                            }

                        }
                    }
                }
            }
        }

    }

    /**
     * 沉浸式状态栏
     */
     private fun setTranslucentStatus() { // 5.0以上系统状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.addFlags(
                WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
            )
            window.statusBarColor = Color.TRANSPARENT;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when (it.action) {
                MotionEvent.ACTION_DOWN -> {
                    firstX = it.x
                    firstY = it.y
                }
                MotionEvent.ACTION_UP -> {
                    secondX = it.x
                    secondY = it.y
                    Log.d("test", "onTouchEvent:${secondX - firstX} ")
                    if (secondX - firstX < -100) {//右滑
                        if (viewModel.currentPos + 1 < 6)
                            viewModel.changeCity(viewModel.currentPos + 1)
                    } else if (secondX - firstX > 100) {//左滑
                        if (viewModel.currentPos - 1 >= 0)
                            viewModel.changeCity(viewModel.currentPos - 1)
                    }
                }
            }
        }
        return true
    }

}