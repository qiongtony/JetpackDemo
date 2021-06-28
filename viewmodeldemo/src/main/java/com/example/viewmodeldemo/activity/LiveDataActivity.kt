package com.example.viewmodeldemo.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodeldemo.CustomApplication
import com.example.viewmodeldemo.R
import com.example.viewmodeldemo.ShareViewModel
import kotlinx.android.synthetic.main.activity_live_data.*
import java.util.*

class LiveDataActivity : AppCompatActivity(), Observer<String> {

    private val stringList = arrayOf("锲而舍之，朽木不折；锲而不舍，金石可镂。",
    "不要失去信心，要坚持不懈，最终会有成果。——钱学森",
    "人生在勤，不索何获。——张衡",
    "人的大脑和肢体一样，多用则灵，不用则废。——茅以升")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        val viewModel =  ViewModelProvider(this.applicationContext as CustomApplication).get(
            ShareViewModel::class.java
        )

        Log.i("WWS", "onCreate content = ${viewModel.content.value ?: ""}")


        btnChange.setOnClickListener {
            viewModel.updateContent(stringList[Random().nextInt(stringList.size)] ?: "空文案")
        }

        viewModel.content.observe(this, this)
    }

    override fun onChanged(t: String?) {
        tvContent.text = t
    }
}