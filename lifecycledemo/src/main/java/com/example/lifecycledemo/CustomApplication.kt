package com.example.lifecycledemo

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner

class CustomApplication :Application(){

    override fun onCreate() {
        super.onCreate()
        // 获取Application的observable
        ProcessLifecycleOwner.get().lifecycle
            .addObserver(MyApplicationObserver())
    }
}