package com.example.roomdemo.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.R
import com.example.roomdemo.bean.Student
import kotlinx.android.synthetic.main.item_student.view.*

class StudentAdapter(val context : Context) : RecyclerView.Adapter<StudentAdapter.MyHolder>() {

    private val dataList = mutableListOf<Student>()

    class MyHolder(view: View) : RecyclerView.ViewHolder(view){
        var tvId: TextView = view.findViewById(R.id.tvId)
        var tvName : TextView = view.findViewById(R.id.tvName)
        var tvAge : TextView = view.findViewById(R.id.tvAge)

        fun bindHolder(student: Student){
            tvId.text = "id:${student.id}"
            tvName.text = "name:${student.name}  2333"
            tvAge.text = "age:${student.age}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.item_student, parent, false))
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
            holder.bindHolder(dataList[position])
    }

    fun setData(dataList : List<Student>){
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }
}