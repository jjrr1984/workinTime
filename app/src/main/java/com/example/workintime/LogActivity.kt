package com.example.workintime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workintime.databinding.ActivityLogBinding
import java.util.*

class LogActivity : AppCompatActivity() {
    private val initialTime: Date = Date()
    private lateinit var binding: ActivityLogBinding
    private var workingHours: Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        workingHours = getIntent().getFloatExtra(hoursParamId, 0.0F)
    }
}