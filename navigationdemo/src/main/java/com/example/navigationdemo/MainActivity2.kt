package com.example.navigationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI

class MainActivity2 : AppCompatActivity() {
    lateinit var navController : NavController
    lateinit var appBarConfiguration : AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        navController = Navigation.findNavController(this, R.id.fragment)
        appBarConfiguration  = AppBarConfiguration.Builder(navController.graph).build()
        // app bar 和nav controller绑定起来
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)


    }

    fun setListener(){
        navController.addOnDestinationChangedListener {
                // 收到切换事件
                controller, destination, arguments ->
            {

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }
}