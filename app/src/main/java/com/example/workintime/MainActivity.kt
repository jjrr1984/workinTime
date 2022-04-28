package com.example.workintime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workintime.databinding.ActivityMainBinding

val hoursParamId = "com.example.workintime.hours"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}