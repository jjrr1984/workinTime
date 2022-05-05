package com.example.workintime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workintime.databinding.ActivityLogBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.schedule

class LogActivity : AppCompatActivity() {
    private val initialTime: Date = Date()
    private lateinit var binding: ActivityLogBinding
    private var remainingMinutes: Int = 0
    private val takeBreakText = "Take a break"
    private val imBackText = "I'm back"
    private var employeeStatus = "working"
    private val dateFormat = SimpleDateFormat("H:mm")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        remainingMinutes = getIntent().getIntExtra(hoursParamId, 0) * 60

        setInitialText()
        setEndTimeText()
        binding.breakButton.setOnClickListener{
            takeBreak()
        }
        binding.cancelButton.setOnClickListener{
            cancelLog()
        }
        startTimer()
    }

    private fun setInitialText(){
        val dateFormatted = dateFormat.format(initialTime)
        val initialText = "- You started your day at ${dateFormatted}\n"
        binding.logTextView.text = initialText
    }

    private fun takeBreak(){
        val currentDate:Date = Date()
        val currentDateFormatted = dateFormat.format(currentDate)

        if(employeeStatus === "working"){
            binding.breakButton.text = imBackText
            employeeStatus = "idle"
            binding.logTextView.append("- You took a break at ${currentDateFormatted}\n")
        }else{
            binding.breakButton.text = takeBreakText
            employeeStatus = "working"
            binding.logTextView.append("- You came back at ${currentDateFormatted}\n")
            setEndTimeText()
            startTimer()
        }
    }

    private fun cancelLog(){
        finish()
    }

    private fun setEndTimeText(){
        var endTime: Date = Date()
        endTime.time +=  remainingMinutes*60*1000
        val endTimeFormatted = dateFormat.format(endTime)
        binding.endTimeTextView.text = "You should end your day at ${endTimeFormatted}"
    }

    private fun startTimer(){
        Timer().schedule(60000) {
            if(employeeStatus === "working") {
                remainingMinutes -= 1
                if(remainingMinutes === 0){
                    endLog()
                }else{
                    startTimer()
                }
            }
        }
    }

    private fun endLog(){
        binding.endTimeTextView.text = "You completed your working time!"
        binding.breakButton.isEnabled = false
        binding.cancelButton.text = "New log"
    }
}