package com.example.roomdemo.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roomdemo.bean.Student
import com.example.roomdemo.database.MyDatabase

/**
 * 用AndroidViewModel是因为创建DB实例需要context，而AndroidViewModel可以获取application
 */
class StudentViewModel(@NonNull application: Application) : AndroidViewModel(application) {
    private var myDataBase : MyDatabase = MyDatabase.getInstance(application)
    lateinit var liveDataStudents : LiveData<List<Student>>

    init {
        liveDataStudents = myDataBase.studentDao().getStudentList()
    }


}