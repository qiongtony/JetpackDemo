package com.example.lifecycledemo

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * 实现与Service生命周期解耦
 */
class MyServiceObserver : LifecycleObserver{

    /**
     * 当Service执行onCreate()方法时，该方法会被自动调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun startGetLocation(){
        Log.d(TAG, "startGetLocation")
    }

    /**
     * 当Service执行onDestroy方法时，该方法会被自动调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public fun stopGetLocation(){
        Log.d(TAG, "stopGetLocation")
    }
}