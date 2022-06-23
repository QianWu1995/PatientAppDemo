package com.qianwu.patientbooking.domain.entity

import com.google.gson.annotations.SerializedName
import java.util.Date

data class AvailableTimes(val availablityMap: Map<String, List<String>>) {
    fun getFlattenedTimes(): List<String>{
        val allAvailableTimes = mutableListOf<String>()
        for(key in availablityMap.keys){
            for(item in availablityMap[key]!!){
                allAvailableTimes.add(item)
            }
        }
        return allAvailableTimes
    }
}

data class AvailableTimesRequest(
    @SerializedName("service_ids") var serviceIds: List<String> = listOf(),
    @SerializedName("zip_code") var zipCode: String? = null
)