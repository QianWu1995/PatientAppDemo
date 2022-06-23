package com.qianwu.patientbooking.data.API

import com.qianwu.patientbooking.domain.entity.Address
import com.qianwu.patientbooking.domain.entity.AddressResponse
import com.qianwu.patientbooking.util.TimeUtil
import com.qianwu.patientbooking.domain.entity.AvailableTimes
import com.qianwu.patientbooking.domain.entity.AvailableTimesRequest
import com.qianwu.patientbooking.domain.entity.CreateAddressRequest
import com.qianwu.patientbooking.domain.entity.Patient
import com.qianwu.patientbooking.domain.entity.PatientResponse
import com.qianwu.patientbooking.domain.entity.Service
import com.qianwu.patientbooking.domain.entity.Visit
import com.qianwu.patientbooking.domain.entity.VisitRequest
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class APIClient @Inject constructor(retrofit: Retrofit) {

    private val apiService = retrofit.create(ApiEndPointInterface::class.java)

    fun getAvailableServicesWithZipCode(zipCode: String): Single<List<Service>> = apiService.getAvailableServicesWithZipCode(zipCode)

    fun getAddress(addressId: String): Single<Address> = apiService.getAddressById(addressId)

    fun createAddress(addressRequest: CreateAddressRequest): Single<AddressResponse> = apiService.createAddress(addressRequest)

    fun createPatient(patient: Patient): Single<PatientResponse> = apiService.createPatient(patient)

    fun createVisit(visitRequest: VisitRequest) = apiService.createVisit(visitRequest)

    fun getTimeAvailability(availableTimesRequest: AvailableTimesRequest): Single<AvailableTimes> =
        apiService.getTimeAvailability(availableTimesRequest).map { TimeUtil.jsonToAvailableTimes(it) }

    fun getVisitById(visitId: String): Single<Visit> = apiService.getVisitById(visitId)
}