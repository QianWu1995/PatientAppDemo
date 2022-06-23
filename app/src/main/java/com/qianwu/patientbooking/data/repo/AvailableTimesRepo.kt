package com.qianwu.patientbooking.data.repo

import com.qianwu.patientbooking.data.API.APIClient
import com.qianwu.patientbooking.domain.entity.AvailableTimes
import com.qianwu.patientbooking.domain.entity.AvailableTimesRequest
import io.reactivex.Single
import javax.inject.Inject

class AvailableTimesRepo @Inject constructor(private val apiClient: APIClient) {

    fun getAvailableTimesForServiceWithZipCode(serviceIds: List<String>, zipCode: String) =
        apiClient.getTimeAvailability(AvailableTimesRequest(serviceIds, zipCode))

    fun getFlattenedAvailableTimesForServiceWithZipCode(serviceIds: List<String>, zipCode: String) =
        apiClient.getTimeAvailability(AvailableTimesRequest(serviceIds, zipCode)).map {
            it.getFlattenedTimes()
        }
}