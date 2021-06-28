package com.example.viewmodeldemo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kunminx.architecture.ui.callback.ProtectedUnPeekLiveData
import com.kunminx.architecture.ui.callback.UnPeekLiveData

/**
 * 全局的VieModel
 */
class ShareViewModel : ViewModel(){
    // 修复数据倒灌的LiveData
    private val _content : UnPeekLiveData<String> = UnPeekLiveData.Builder<String>().setAllowNullValue(true)
        .create()
    val content : ProtectedUnPeekLiveData<String> = _content
    /*private val _content : MutableLiveData<String> = MutableLiveData()
    val content  : LiveData<String> = _content*/

    public fun updateContent(content : String){
        Log.i(javaClass.simpleName, "WWS updateContent content = $content")
        this._content.value = content
    }
}