package com.example.workintime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.workintime.databinding.ActivityMainBinding
import com.google.android.material.slider.Slider

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
            Log.i("hey","Hola")
        }
    }

    private fun updateHoursText(hours: Float){
        binding.hoursTextView.setText("$hours hours")
    }
}