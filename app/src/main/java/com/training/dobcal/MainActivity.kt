package com.training.dobcal

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var textSelectedDate : TextView? = null

    var textMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePickerBtn : Button = findViewById(R.id.datePickerBtn)
        textSelectedDate = findViewById(R.id.text_selected_date)
        textMinutes = findViewById(R.id.text_minutes)
        datePickerBtn.setOnClickListener {
            selectDate()
        }
    }

    private fun selectDate() {
        val myCalendar = Calendar.getInstance()

        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

       val dpd = DatePickerDialog(this,
            {view, selectedYear, selectedMonth, selectedDay ->
                Toast.makeText(this,"Date Picker Work",Toast.LENGTH_LONG).show()

                val selectedDate = "${selectedDay}/$selectedMonth/$selectedYear"
                textSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000

                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        textMinutes?.text = differenceInMinutes.toString()
                    }
                }

            },
            year,
            month,
            day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}