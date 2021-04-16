package com.example.navigationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

/**
 * 实现fragment间最简单的跳转、跳转动画、传参
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        findNavController(R.id.nav_host_fragment).navigatorProvider.addNavigator(FixFragmentNavigator(this, navHostFragment.childFragmentManager,
            navHostFragment.id))
    }
}
