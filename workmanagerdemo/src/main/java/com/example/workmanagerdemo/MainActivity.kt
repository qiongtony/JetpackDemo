
package com.example.workmanagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 后台任务触发条件
        val constraints = Constraints.Builder()
                // 正在充电
            .setRequiresCharging(true)
                // 网络已连接
            .setRequiredNetworkType(NetworkType.CONNECTED)
                // 手机电量充足
            .setRequiresBatteryNotLow(true)
            .build()
        // 设置任务请求，将条件和执行的具体任务关联到一起
        // 传递的数据只能是一些小的基本数据类型，数据最大不能超过10KB
        val inputData = Data.Builder().putString("input_data", "hello world!").build()
        val uploadWorkRequest = OneTimeWorkRequest.Builder(UploadLogWorker::class.java)
                // 设置延时时间
            /*.setInitialDelay(10, TimeUnit.SECONDS)   */
                // 设置指数退避算法
            .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
                // 设置tag标签，
            .addTag("UploadTag")
                // 设置传的数据
            .setInputData(inputData)
            .setConstraints(constraints)
            .build()
        // 将任务交给系统
        WorkManager.getInstance(this).enqueue(uploadWorkRequest)
        // 获取任务
        WorkManager.getInstance(this).getWorkInfosByTag("UploadTag")
        // 取消任务
        WorkManager.getInstance(this).cancelAllWorkByTag("UploadTag")
        // 监听任务状态
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(uploadWorkRequest.id)
            .observe(this, Observer {
                Log.w("OnChanged() ->", "workInfo: outputData = ${it.outputData.getString("output_data")}")
            })
    }
}
