package com.example.covidvaccination

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covidvaccination.databinding.SampleVaccineBinding

class VaccineAdapter(var centerList: ArrayList<VaccineModel>) :
    RecyclerView.Adapter<VaccineAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: SampleVaccineBinding) :
        RecyclerView.ViewHolder(binding.root)


    fun filterList(filterList: ArrayList<VaccineModel>) {
        centerList = filterList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SampleVaccineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return centerList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            binding.apply {
                with(centerList[position]) {
                    tvCentreName.text = this.centerName.toString()
                    tvCentreLocation.text = this.centerAddress.toString()
                    tvCentreTimings.text = "From :${this.centerFromTime} To :${this.centerToTime.toString()}"
                    tvVaccineName.text = this.vaccineName.toString()
                    tvFee.text = this.feeType.toString()
                    tvAvailability.text = "Availability : ${this.availableCapacity.toString()}"
                }
            }
        }
    }
}
