package com.example.lifecycledemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var myLocationListener: MyLocationListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.openNetPage).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        findViewById<Button>(R.id.startService).setOnClickListener {
            startService(Intent(this, MyService::class.java))
        }
        findViewById<Button>(R.id.stopService).setOnClickListener {
            stopService(Intent(this, MyService::class.java))
        }
        myLocationListener = MyLocationListener(this, object : MyLocationListener.OnLocationChangedListener{
            override fun onChanged(latitude: Double, longitude: Double) {
                Log.d(TAG, "onChanged latitude = $latitude longitude = $longitude")
            }
        })
        lifecycle.addObserver(myLocationListener)

    }
}
