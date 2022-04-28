package com.example.workintime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workintime.databinding.ActivityLogBinding
import java.text.SimpleDateFormat
import java.util.*

class LogActivity : AppCompatActivity() {
    private val initialTime: Date = Date()
    private lateinit var binding: ActivityLogBinding
    private var remainingMinutes: Int = 0
    private val takeBreakText = "Take a break"
    private val imBackText = "I'm back"
    private var employeeStatus = "working"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        remainingMinutes = getIntent().getIntExtra(hoursParamId, 0) * 60

        setInitialText()
        binding.breakButton.setOnClickListener{
            takeBreak()
        }
        binding.cancelButton.setOnClickListener{
            cancelLog()
        }
    }

    private fun setInitialText(){
        val dateFormat = SimpleDateFormat("H:m")
        val dateFormatted = dateFormat.format(initialTime)
        val initialText = "- You started your day at ${dateFormatted}\n"
        binding.logTextView.text = initialText
    }

    private fun takeBreak(){
        if(employeeStatus === "working"){
            val currentDate:Date = Date()
            binding.breakButton.text = imBackText
            employeeStatus = "idle"
        }else{
            binding.breakButton.text = takeBreakText
            employeeStatus = "working"
        }
    }

    private fun cancelLog(){
        finish()
    }
}