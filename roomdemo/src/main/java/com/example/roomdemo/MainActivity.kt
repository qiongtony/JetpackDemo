package com.example.roomdemo

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.adapter.StudentAdapter
import com.example.roomdemo.bean.Student
import com.example.roomdemo.database.MyDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    companion object{
        val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    lateinit var adapter : StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvStudent.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        adapter = StudentAdapter(this)
        rvStudent.adapter = adapter

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permission, 100)
        }
        btnAddStudent.setOnClickListener {
            addStudent()
        }
    }

    fun addStudent(){
        GlobalScope.launch {
            withContext(Dispatchers.IO){
                MyDatabase.getInstance(applicationContext).studentDao().insertStudent(Student(10, "吴为山", "100"))
            }
        }
    }

    private fun loadData() {
        GlobalScope.launch(Dispatchers.Main) {
            var list: List<Student>? = null
            withContext(Dispatchers.IO) {
                list = getStudentList()
            }

            notifyRecyclerView(list!!)
        }
    }


    suspend fun getStudentList(): List<Student> {
        val list = mutableListOf<Student>()
        list.addAll(MyDatabase.getInstance(this).studentDao().getStudentList())
        return list
    }

    suspend fun notifyRecyclerView(list: List<Student>) {
        adapter.setData(list)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        loadData()

    }


}
