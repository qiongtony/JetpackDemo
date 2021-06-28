package com.example.viewmodeldemo

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class CustomApplication : Application(), ViewModelStoreOwner{

    companion object{
        val modelStore : ViewModelStore = ViewModelStore()
    }





    override fun onCreate() {
        super.onCreate()
    }

    override fun getViewModelStore(): ViewModelStore {
        return modelStore
    }

}