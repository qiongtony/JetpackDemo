package com.example.roomdemo.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdemo.bean.Student

/**
 * 定义数据库的操作
 */
@Dao
interface StudentDao {
    @Insert
    fun insertStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

    @Update
    fun updateStudent(student: Student)

    @Query("SELECT * FROM student")
    // 用LiveData包装List<Student>
    fun getStudentList() : LiveData<List<Student>>

    /**
     * 根据student的id查找，这里可以看出来用:形参名可以获取到方法里的形参
     */
    @Query("SELECT * FROM student WHERE id = :id")
    fun getStudentById(id:Int) : Student?
}