package com.example.lifecycledemo

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * 实现自动观察生命周期做出处理，而不是要外部去手动调用
 * 实现观察者，将观察者添加到别观察者的观察者监听map内
 * 问题1：需要手动解绑吗
 * 不需要
 * 屏幕旋转时会自动销毁重建
 * Activity/Fragment实现了Observable，在相应生命周期会告知Observable，然后Observable通知给订阅的Observer
 * Observable->Observer
 */
class MyLocationListener(val context : Context, val locationChangedListener: OnLocationChangedListener) :LifecycleObserver {

    init {
        // 初始化操作
        initLocationManager()
    }

    fun initLocationManager(){

    }

    /**
     * 当Activity/Fragment执行onResume()方法时，该方法会被自动调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun startGetLocation(){
        Log.w(TAG, "startGetLocation")
    }

    /**
     * 当Activity/Fragment执行onPause方法时，该方法会被自动调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public fun stopGetLocation(){
        Log.w(TAG, "stopGetLocation")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public fun destroy(){
        Log.w(TAG, "destroy")
    }

    interface OnLocationChangedListener{
        /**
         * 位置改变
         */
        fun onChanged(latitude : Double, longitude : Double)
    }
}