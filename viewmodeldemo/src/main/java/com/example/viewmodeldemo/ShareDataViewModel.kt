package com.example.viewmodeldemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareDataViewModel : ViewModel(){
    val progress : MutableLiveData<Int> = MutableLiveData()

    init {
        progress.value = 0
    }


    override fun onCleared() {
        super.onCleared()
    }
}