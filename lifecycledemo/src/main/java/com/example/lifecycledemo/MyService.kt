package com.example.lifecycledemo

import androidx.lifecycle.LifecycleService

class MyService : LifecycleService(){
    private val serviceObserver : MyServiceObserver = MyServiceObserver()

    init {
        lifecycle.addObserver(serviceObserver)
    }
}