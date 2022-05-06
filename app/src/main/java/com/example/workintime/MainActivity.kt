package com.example.workintime

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workintime.databinding.ActivityMainBinding

val hoursParamId = "com.example.workintime.hours"
val CHANNEL_ID = "not_channel_id"
val notificationID = 666

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //  Listener for changes in slider
        binding.initialHoursSlider.addOnChangeListener { slider, value, fromUser ->
            updateHoursText(value)
        }

        //  Listener for start button
        binding.startButton.setOnClickListener{
            startDay()
        }
    }

    private fun updateHoursText(hours: Float){
        binding.hoursTextView.setText("$hours hours")
    }

    private fun startDay(){
        val hours = binding.initialHoursSlider.value.toInt()
        val intent = Intent(this, LogActivity::class.java).apply {
            putExtra(hoursParamId, hours)
        }
        startActivity(intent)
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}