package com.example.covidvaccination

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        tv_pincode.setOnClickListener(View.OnClickListener { view->

            ll_pincode.visibility = View.VISIBLE
            ll_district.visibility = View.GONE
            tv_pincode.setBackgroundColor(Color.parseColor("#29B6F6"))
            tv_district.setBackgroundColor(Color.TRANSPARENT)
            tv_district.setBackgroundResource(R.drawable.button_background)



        })

        tv_district.setOnClickListener(View.OnClickListener { view->

            ll_district.visibility = View.VISIBLE
            ll_pincode.visibility = View.GONE
            tv_district.setBackgroundColor(Color.parseColor("#29B6F6"))
            tv_pincode.setBackgroundColor(Color.TRANSPARENT)
            tv_pincode.setBackgroundResource(R.drawable.button_background)


        })

        btn_checkAvailability.setOnClickListener(View.OnClickListener { view->

            val pincode = et_pincode.text.toString()

            if (pincode.length != 6){
                Toast.makeText(this, "Please enter a valid pincode", Toast.LENGTH_SHORT).show()
            }else{


                val calendar = Calendar.getInstance()
                val year  = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePicker = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->

                       var dateString = "$dayOfMonth-${month+1}-$year"
                    Log.i("date", dateString.toString() )

                    var intent = Intent(this, VaccineDetailActivity::class.java)
                    intent.putExtra("Pincode", pincode)
                    intent.putExtra("Date", dateString)
                    startActivity(intent)
                },

                    year,
                month,
                day)

                datePicker.show()




            }



        })
    }


}