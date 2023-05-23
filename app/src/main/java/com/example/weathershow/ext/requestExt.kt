package com.example.weathershow.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathershow.net.GlobalCoroutineExceptionHandler
import kotlinx.coroutines.*

inline fun ViewModel.requestMain(
    errCode: Int = -1, errMsg: String = "", report: Boolean = false,
    noinline block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch(GlobalCoroutineExceptionHandler(errCode, errMsg, report)) {
        block.invoke(this)
    }
}

inline fun ViewModel.requestIO(
    errCode: Int = -1, errMsg: String = "", report: Boolean = false,
    noinline block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch(Dispatchers.IO + GlobalCoroutineExceptionHandler(errCode, errMsg, report)) {
        block.invoke(this)
    }
}

inline fun ViewModel.delayMain(
    errCode: Int = -1, errMsg: String = "", report: Boolean = false, delayTime: Long,
    noinline block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch(GlobalCoroutineExceptionHandler(errCode, errMsg, report)) {
        withContext(Dispatchers.IO) {
            delay(delayTime)
        }
        block.invoke(this)
    }
}
