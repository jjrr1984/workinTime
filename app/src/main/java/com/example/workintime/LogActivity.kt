package com.example.workintime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workintime.databinding.ActivityLogBinding
import java.util.*

class LogActivity : AppCompatActivity() {
    private val initialTime: Date = Date()
    private val hours: Float =
        intent.getStringExtra(hoursParamId)!!.toFloat()
    private lateinit var binding: ActivityLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}