package com.example.covidvaccination

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.covidvaccination.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    @SuppressLint("LogConditional")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        supportActionBar!!.hide()

        binding.apply {

            tvPincode.setOnClickListener(View.OnClickListener { _ ->

                llPincode.visibility = View.VISIBLE
                llDistrict.visibility = View.GONE
                tvPincode.setBackgroundColor(Color.parseColor("#29B6F6"))
                tvDistrict.setBackgroundColor(Color.TRANSPARENT)
                tvDistrict.setBackgroundResource(R.drawable.button_background)


            })

            tvDistrict.setOnClickListener(View.OnClickListener { _ ->

                llDistrict.visibility = View.VISIBLE
                llPincode.visibility = View.GONE
                tvDistrict.setBackgroundColor(Color.parseColor("#29B6F6"))
                tvPincode.setBackgroundColor(Color.TRANSPARENT)
                tvPincode.setBackgroundResource(R.drawable.button_background)


            })

            btnCheckAvailability.setOnClickListener(View.OnClickListener { _ ->

                val pincode = etPincode.text.toString()

                if (pincode.length != 6) {
                    Toast.makeText(
                        this@MainActivity,
                        "Please enter a valid pincode",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {


                    val calendar = Calendar.getInstance()
                    val year = calendar.get(Calendar.YEAR)
                    val month = calendar.get(Calendar.MONTH)
                    val day = calendar.get(Calendar.DAY_OF_MONTH)

                    val datePicker = DatePickerDialog(
                        this@MainActivity,
                        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->

                            var dateString = "$dayOfMonth-${month + 1}-$year"
                            Log.i("date", dateString.toString())

                            var intent =
                                Intent(this@MainActivity, VaccineDetailActivity::class.java)
                            intent.putExtra("Pincode", pincode)
                            intent.putExtra("Date", dateString)
                            startActivity(intent)
                        },

                        year,
                        month,
                        day
                    )

                    datePicker.show()


                }


            })
        }
    }


}