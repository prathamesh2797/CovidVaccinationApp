package com.example.covidvaccination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.covidvaccination.databinding.SampleVaccineBinding

class VaccineAdapter(var centerList: ArrayList<VaccineModel>) :
    RecyclerView.Adapter<VaccineAdapter.ViewHolder>() {

    private lateinit var binding: SampleVaccineBinding


    inner class ViewHolder(itemView: SampleVaccineBinding) : RecyclerView.ViewHolder(itemView.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.sample_vaccine,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    fun filterList(filterList: ArrayList<VaccineModel>) {
        centerList = filterList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {

        return centerList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            binding.apply {
                with(centerList[position]) {
                    tvCentreName.text = this.centerName.toString()
                    tvCentreLocation.text = this.centerAddress.toString()
                    tvCentreTimings.text =
                        "From :${this.centerFromTime} To :${this.centerToTime.toString()}"
                    tvVaccineName.text = this.vaccineName.toString()
                    tvFee.text = this.feeType.toString()
                    tvAvailability.text = "Availability : ${this.availableCapacity.toString()}"
                }
            }
        }
    }
}
