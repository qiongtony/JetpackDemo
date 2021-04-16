package com.example.navigationdemo

import android.animation.Animator
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val userName = it.getString("user_name")
            val age = it.getInt("age")
            Log.d("WWS", "SecondFragment userName = $userName age = $age")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("WWS", "SecondFragment createTime = ${SystemClock.elapsedRealtime()}")
        view.animate().alpha(0.5f)
            .setDuration(2000L).setStartDelay(1300).setListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    Log.e("WWS", "SecondFragment onAnimationEnd")
                    // 这里可以传bundle，相当于setArgument
                    Navigation.findNavController(view)
                        .navigate(R.id.action_secondFragment_to_thirdFragment)
                }

                override fun onAnimationCancel(animation: Animator?) {
                    Log.e("WWS", "SecondFragment onAnimationStart")
                }

                override fun onAnimationStart(animation: Animator?) {
                    Log.e("WWS", "SecondFragment onAnimationStart")
                }

            }).start()


    }
}