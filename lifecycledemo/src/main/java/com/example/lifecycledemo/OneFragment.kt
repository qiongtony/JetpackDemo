package com.example.lifecycledemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle


/**
 * A simple [Fragment] subclass.
 * Use the [OneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OneFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    override fun onResume() {
        super.onResume()
        Log.w(TAG, "onResume")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
    }

    override fun onPause() {
        super.onPause()
        Log.w(TAG, "onPause")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        lifecycle.currentState
        Log.w(TAG, "onHiddenChanged hidden = $hidden")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myLocationListener = MyLocationListener(context!!, object : MyLocationListener.OnLocationChangedListener{
            override fun onChanged(latitude: Double, longitude: Double) {
                Log.d(TAG, "onChanged latitude = $latitude longitude = $longitude")
            }
        })
        lifecycle.addObserver(myLocationListener)
    }
}