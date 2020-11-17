package com.example.workmanagerdemo

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadLogWorker(context : Context, workerParams : WorkerParameters) : Worker(context, workerParams){
    override fun doWork(): Result {
        // 获取传过来的数据
        val inputData = inputData.getString("input_data")
        // 任务执行完后返回数据
        val outputData = Data.Builder().putString("output_data", "$inputData Task success!").build()
        // Result有success---成功，failure---失败,retry---重试三种返回值
        return Result.success(outputData)
    }

}