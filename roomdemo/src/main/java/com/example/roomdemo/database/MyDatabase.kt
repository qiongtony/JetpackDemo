package com.example.roomdemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdemo.bean.Student
import com.example.roomdemo.dao.StudentDao

/**
 * 这里等价于DatabaseHelper吧，为啥要是抽象类呢
 * 在这里
 */
@Database(entities = [Student::class],
    version = 1,
/*是否到处schema文件默认为true */
    exportSchema = true)
abstract class MyDatabase : RoomDatabase(){

    companion object{
        const val DATABASE_NAME = "my_db"
        @Volatile
        var databaseInstance : MyDatabase? = null
        fun getInstance(context: Context) : MyDatabase = databaseInstance ?: synchronized(this){
            databaseInstance ?:
                 Room.databaseBuilder<MyDatabase>(
                    context.applicationContext,
                    MyDatabase::class.java,
                    DATABASE_NAME)
                     // 从assets目录下读取students.db，这里要注意类似要是可空?，不然一直爆expected：notNull=true，found：notNull=false
                     .createFromAsset("students.db")
                     .build().also {
                     databaseInstance = it
                 }
        }

    }

    abstract fun studentDao() : StudentDao
}