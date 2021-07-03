package com.example.covidvaccination

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_vaccine_detail.*
import org.json.JSONException
import org.json.JSONObject
import kotlin.collections.ArrayList

class VaccineDetailActivity : AppCompatActivity() {

    var vaccineModel = ArrayList<VaccineModel>()
    private var adapter:VaccineAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccine_detail)

        supportActionBar!!.title= "Vaccination Centers"
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        var pincode = intent.getStringExtra("Pincode")
        var date = intent.getStringExtra("Date")

        getAppointmentDetails(pincode, date)

        et_search.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

                filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }

        })

    }

    private fun filter(toString: String){
        var filterList= ArrayList< VaccineModel>()

        for(item in vaccineModel){
            if (item.centerAddress.toLowerCase().contains(toString.toLowerCase()) || item.centerName.toLowerCase().contains(toString.toLowerCase())){
                filterList.add(item)
            }
        }
        adapter!!.filterList(filterList)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home){
            vaccineModel.clear()
            finish()



        }
        return super.onOptionsItemSelected(item)
    }

    private fun getAppointmentDetails(pincode: String, date: String){
        val url ="https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+pincode+"&date="+date
        Log.i("URL", url.toString())
        arc_loader.start()
        val queue = Volley.newRequestQueue(this)


        val request = JsonObjectRequest(
            Request.Method.GET, url, null,{
                response ->

            try{
                val centerArray = response.getJSONArray("sessions")
                if (centerArray.length().equals(0)) {
                    Toast.makeText(this, "No Vaccination Centers Found!!", Toast.LENGTH_SHORT)
                        .show()

                }

                for (i in 0 until  centerArray.length()){


                    var jsonObject: JSONObject =centerArray.getJSONObject(i)
                    var centerName:String = jsonObject.getString("name")
                    var centerAddress:String = jsonObject.getString("address")
                    var centerFromTime:String = jsonObject.getString("from")
                    var centerToTime:String = jsonObject.getString("to")
                    var feeType:String = jsonObject.getString("fee_type")
                    var availableCapacity = jsonObject.getInt("available_capacity")
                    var ageLimit = jsonObject.getInt("min_age_limit")
                    var vaccineName = jsonObject.getString("vaccine")

                    val center = VaccineModel(centerName, centerAddress, centerFromTime, centerToTime, feeType, ageLimit, vaccineName, availableCapacity)

                    vaccineModel.add(center)




                }
                arc_loader.stop()
                arc_loader.visibility = View.GONE
                recycler_view.visibility = View.VISIBLE

                adapter = VaccineAdapter(vaccineModel)
                recycler_view.layoutManager = LinearLayoutManager(this)
                recycler_view.adapter = adapter

            }catch (e: JSONException){

                e.printStackTrace()
            }
        },
            {
                    error ->

                Toast.makeText(this, "Failed To Load Data", Toast.LENGTH_SHORT).show()
            })

        queue.add(request)
    }

}