package com.example.broadcastreceiversbasic

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.broadcastreceiversbasic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var receiver: BroadcastReceiver
    lateinit var context: Context   //For Manifest-Registered Broadcast Receivers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        //Start of Context-registered Broadcast Receivers
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Toast.makeText(context, intent?.action, Toast.LENGTH_SHORT).show()
            }
        }
        registerReceiver(receiver, filter)
        //End of Context-registered Broadcast Receivers

        binding.btnLaunch.setOnClickListener {//Manifest-Registered Broadcast Receivers

            sendBroadcast(Intent(context, MyReceiver::class.java))
        }
    }

    override fun onDestroy() {
        unregisterReceiver(receiver) //Context-registered Broadcast Receivers
        super.onDestroy()
    }
}