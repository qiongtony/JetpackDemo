package com.example.navigationdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_new_main.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewMainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewMainFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_new_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSendNotification.setOnClickListener {
            sendNotification()
        }
    }

    private fun sendNotification(){
        if (requireActivity() == null){
            return;
        }
        val CHANNEL_ID = "deepLink"
        val notificationId = 100010
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, "ChannelName", importance)
            channel.description = "description"
            val notificationManager = requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        val builder = NotificationCompat.Builder(requireActivity(), CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("DeepLinkDemo")
            .setContentText("Hello World!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(getPendingIntent())
            .setAutoCancel(true)
        val notificationManager = NotificationManagerCompat.from(requireContext())
        notificationManager.notify(notificationId, builder.build())
    }

    private fun getPendingIntent() : PendingIntent? {
        if (activity != null){
            val args = Bundle()
            args.putString("params", "ParamsFromNotification_HelloMichael")
            return Navigation.findNavController(requireActivity(),R.id.fragment)
                .createDeepLink()
                .setGraph(R.navigation.nav_graph_2)
                .setDestination(R.id.deepLinkFragment)
                .setArguments(args)
                .createPendingIntent()
        }else{
            return null
        }
    }
}