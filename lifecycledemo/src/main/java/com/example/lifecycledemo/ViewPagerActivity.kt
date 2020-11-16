package com.example.lifecycledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.lifecycledemo.adapter.ViewPagerAdapter
import com.example.lifecycledemo.helper.FragmentVisibleHelper
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        val fragments = mutableListOf<Fragment>(OneFragment(), TwoFragment())
        view_pager.adapter =
            ViewPagerAdapter(supportFragmentManager, fragments, arrayOf<String>("one", "two"));
        view_pager.offscreenPageLimit = fragments.size;

        FragmentVisibleHelper.listenHiddenChange(view_pager, fragments);
        view_pager.currentItem = 0
    }
}