package com.example.weathershow.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weathershow.ui.repo.WeatherRepoSitory
import com.example.weathershow.ui.viewmodel.WeatherViewModel

object ViewModelUtils {
    fun provideMainViewModelFactory(
    ): MainViewModelFactory {
        return MainViewModelFactory(WeatherRepoSitory())
    }
}

class MainViewModelFactory(
    private val repository: WeatherRepoSitory
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(repository) as T
    }

}