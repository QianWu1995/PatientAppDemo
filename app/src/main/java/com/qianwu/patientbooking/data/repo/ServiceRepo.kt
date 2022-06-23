package com.qianwu.patientbooking.data.repo

import com.qianwu.patientbooking.data.API.APIClient
import javax.inject.Inject

class ServiceRepo @Inject constructor(val apiClient: APIClient){

    fun getServiceByZipCode(zipCode: String) = apiClient.getAvailableServicesWithZipCode(zipCode)

    fun getServiceAddressById(id: String) = apiClient.getAddress(id)

}