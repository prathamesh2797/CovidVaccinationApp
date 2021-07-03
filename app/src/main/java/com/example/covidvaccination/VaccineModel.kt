package com.example.covidvaccination

data class VaccineModel(
    val centerName: String,
    val centerAddress: String,
    val centerFromTime: String,
    val centerToTime: String,
    val feeType: String,
    val ageLimit: Int,
    val vaccineName: String,
    val availableCapacity: Int)

