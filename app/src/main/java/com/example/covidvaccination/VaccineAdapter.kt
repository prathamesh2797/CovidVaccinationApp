package com.example.covidvaccination

import android.view.LayoutInflater
import android.widget.Adapter
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VaccineAdapter(var centerList: ArrayList<VaccineModel>):
    RecyclerView.Adapter<VaccineAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val tvCenterName : TextView = itemView.findViewById(R.id.tv_centreName)
        val tvCenterAddress : TextView = itemView.findViewById(R.id.tv_centreLocation)
        val tvCenterTimings : TextView = itemView.findViewById(R.id.tv_centreTimings)
        val tvVaccineName : TextView = itemView.findViewById(R.id.tv_vaccine_name)
        val tvVaccineFee : TextView = itemView.findViewById(R.id.tv_fee)
        val tvAgeLimit : TextView = itemView.findViewById(R.id.tv_age_limit)
        val tvAvailability : TextView = itemView.findViewById(R.id.tv_availability)

    }

    fun filterList(filterList: ArrayList<VaccineModel>){

        centerList =filterList
notifyDataSetChanged()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.sample_vaccine, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return centerList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val center = centerList[position]
        holder.tvCenterName.text = center.centerName
        holder.tvCenterAddress.text = center.centerAddress
        holder.tvCenterTimings.text = ("From :" + center.centerFromTime + " To :"+ center.centerToTime)
        holder.tvVaccineName.text = center.vaccineName
        holder.tvVaccineFee.text = center.feeType
        holder.tvAgeLimit.text = ("Age Limit: " + center.ageLimit.toString())
        holder.tvAvailability.text = ("Availability : " + center.availableCapacity.toString())


    }
}
