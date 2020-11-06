package com.example.navigationdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 直接findViewById设onClickListener为什么要加run？而且在onCreateView后我们就可以像databing一样直接拿到view了。。。
        // 方式1，找到Controller执行action
        btnToSecondFragment.setOnClickListener {
                    val args = Bundle()
                    args.putString("user_name", "wuweishan")
                    args.putInt("age", 100)
                    Navigation.findNavController(view)
                            // 这里可以传bundle，相当于setArgument
                        .navigate(R.id.action_mainFragment_to_secondFragment, args)
        }
        // 方式2
        /*btnToSecondFragment.setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_secondFragment)
        }*/
    }
}