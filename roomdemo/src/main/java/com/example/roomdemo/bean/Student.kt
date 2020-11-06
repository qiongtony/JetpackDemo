package com.example.roomdemo.bean

import androidx.room.ColumnInfo
import androidx.room.Ignore
import androidx.room.PrimaryKey

data class Student(@PrimaryKey(autoGenerate = true)
                    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
                   val id: Int,
                   @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
                   val name : String,
                   @ColumnInfo(name = "age", typeAffinity = ColumnInfo.TEXT)
                   var age : String
) {

    /**
     * 由于Room只能识别和使用一个构造器，除供Room使用的构造器外，其余构造器要用Ignore标签
     * 另外@Ignore标签还可用与字段，表示该字段不想被持久化
     */
    @Ignore
    constructor(id: Int, name :String) : this(id, name, "0") {

    }
}