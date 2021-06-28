package com.example.viewmodeldemo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodeldemo.R
import com.example.viewmodeldemo.TimerViewModel
import kotlinx.android.synthetic.main.activity_live_data.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponent()

        openShareActivity.setOnClickListener {
            startActivity(Intent(this, ShareActivity::class.java))
        }

        openLiveDataActivity.setOnClickListener {
            startActivity(Intent(this, LiveDataActivity::class.java))
        }
    }

    fun initComponent() : Unit{
        Log.w(javaClass.simpleName, "initComponent")
        // ViewModelProvider旋转屏幕不会重建vm，因为VMP在onSaveInstance会重建
        val timerViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)
        // observeForever不管页面处于什么状态都能收到通知，页面销毁需要手动remove
        timerViewModel.currentSecond.observe(this, Observer<Int> { second ->
            runOnUiThread {
                tvTime.text = "TIME:${second}"
            }
        })
        timerViewModel.startTiming()
    }
}
