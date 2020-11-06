package com.example.viewmodeldemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

/**
 * vm在旋转屏幕时不会被销毁，所以数据能够保存
 */
class TimerViewModel() : ViewModel(){
    var timer : Timer? = null
    var currentSecond : MutableLiveData<Int> = MutableLiveData<Int>()
    var timeChangeListener: OnTimeChangeListener? = null

    fun startTiming() : Unit{
        if (timer == null){
            timer = Timer()
            currentSecond.value = 0
            val timerTask = object : TimerTask(){
                override fun run() {
                    // 在子线程要用post
                    currentSecond.postValue(currentSecond.value?.plus(1))
                }
            }
            timer!!.schedule(timerTask, 1000, 1000)

        }else{

        }
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    open interface OnTimeChangeListener{
        fun onTimeChanged(currentSecond : Int)
    }
}