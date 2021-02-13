package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text
import java.lang.System.currentTimeMillis
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // grab the select date button by ID
        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener{view->
            clickDatePicker(view)
        }

    }

    fun clickDatePicker(view:View){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->

                    val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/${selectedYear}"
                    val tvSelectedDate : TextView = findViewById(R.id.tvSelectedDate)
                    tvSelectedDate.text = selectedDate
                    val simpleDateFormat = SimpleDateFormat("dd/mm/yyyy",Locale.ENGLISH)
                    val theDate = simpleDateFormat.parse(selectedDate)
                    val selectedDateInMinutes = theDate!!.time / 60000
                    val currentDate = simpleDateFormat.parse(simpleDateFormat.format((currentTimeMillis())))
                    val currentDateToMinutes = currentDate!!.time / 60000
                    val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes
                    val tvSelectedDateInMinutes : TextView = findViewById(R.id.tvSelectedDateInMinute)
                    tvSelectedDateInMinutes.text = differenceInMinutes.toString()

                },year,month,day)

        datePickerDialog.datePicker.maxDate = Date().time-86400000
        datePickerDialog.show()
    }
}