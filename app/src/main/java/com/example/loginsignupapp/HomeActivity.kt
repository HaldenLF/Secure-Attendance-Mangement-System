package com.example.loginsignupapp

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.text.SimpleDateFormat
import java.util.Date

class HomeActivity : AppCompatActivity() {

    private var checkInTime: String? = null
    private var checkOutTime: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val checkInButton = findViewById<Button>(R.id.checkinBtn)
        val checkOutButton = findViewById<Button>(R.id.checkoutBtn)
        val checkAttendanceButton = findViewById<Button>(R.id.checkAttendanceBtn)
        val attendanceView = findViewById<TextView>(R.id.attendanceView)

        checkInButton.setOnClickListener {
            handleCheckIn()
        }

        checkOutButton.setOnClickListener {
            handleCheckOut()
        }

        checkAttendanceButton.setOnClickListener {
            attendance()
        }
    }
    private fun handleCheckIn() {
        val currentTime = currentTime()
        checkInTime = currentTime
    }

    private fun handleCheckOut() {
        if (checkInTime != null) {
            val currentTime = currentTime()
            checkOutTime = currentTime
        }
        else {
            Toast.makeText(this, "Please check in first", Toast.LENGTH_SHORT).show()
        }
    }

    private fun currentTime(): String {
        val SDF = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val currentDate = Date()
        return SDF.format(currentDate)
    }

    private fun attendance() {
        val attendanceText = when {
            checkInTime != null && checkOutTime != null -> {
                "Check in: $checkInTime\nCheck out: $checkOutTime"
            }
            checkOutTime != null -> {
                "Check in: $checkOutTime"
            }
            else -> {
                "Attendance: Not checked in or out yet."
            }
        }
        val attendanceTextView = findViewById<TextView>(R.id.attendanceView)
        attendanceTextView.text = attendanceText
    }
}

































//class HomeActivity : AppCompatActivity() {
//
//    private var checkInTime: String? = null
//    private var checkOutTime: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
//
//        val checkInButton = findViewById<Button>(R.id.checkinBtn)
//        val checkOutButton = findViewById<Button>(R.id.checkoutBtn)
//        val checkAttendanceButton = findViewById<Button>(R.id.checkAttendanceBtn)
//        val attendanceView = findViewById<TextView>(R.id.attendanceView)
//
//        checkInButton.setOnClickListener {
//            handleCheckIn()
//        }
//
//        checkOutButton.setOnClickListener {
//            handleCheckOut()
//        }
//
//        checkAttendanceButton.setOnClickListener {
//            attendance()
//        }
//    }
//    private fun handleCheckIn() {
//        val currentTime = currentTime()
//        checkInTime = currentTime
//    }
//
//    private fun handleCheckOut() {
//        if (checkInTime != null) {
//            val currentTime = currentTime()
//            checkOutTime = currentTime
//        }
//        else {
//            Toast.makeText(this, "Please check in first", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun currentTime(): String {
//        val SDF = SimpleDateFormat("yyyy-MM-dd HH:mm")
//        val currentDate = Date()
//        return SDF.format(currentDate)
//    }
//
//    private fun attendance() {
//        val attendanceText = when {
//            checkInTime != null && checkOutTime != null -> {
//                "Check in: $checkInTime\nCheck out: $checkOutTime"
//            }
//            checkOutTime != null -> {
//                "Check in: $checkOutTime"
//            }
//            else -> {
//                "Attendance: Not checked in or out yet."
//            }
//        }
//        val attendanceTextView = findViewById<TextView>(R.id.attendanceView)
//        attendanceTextView.text = attendanceText
//    }
//}