package com.qianwu.patientbooking.data.API

import com.google.gson.JsonObject
import com.qianwu.patientbooking.domain.entity.Address
import com.qianwu.patientbooking.domain.entity.AddressResponse
import com.qianwu.patientbooking.domain.entity.AvailableTimesRequest
import com.qianwu.patientbooking.domain.entity.CreateAddressRequest
import com.qianwu.patientbooking.domain.entity.Patient
import com.qianwu.patientbooking.domain.entity.PatientResponse
import com.qianwu.patientbooking.domain.entity.Service
import com.qianwu.patientbooking.domain.entity.Visit
import com.qianwu.patientbooking.domain.entity.VisitRequest
import com.qianwu.patientbooking.domain.entity.VisitResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPointInterface {

    @Headers("Accept: application/json")
    @GET("v1/services")
    fun getAvailableServicesWithZipCode(@Query("zip_code")zipCode: String): Single<List<Service>>

    @Headers("Content-Type: application/json",
             "Accept: application/json")
    @POST("v1/patients")
    fun createPatient(@Body patient: Patient): Single<PatientResponse>

    @Headers("Content-Type: application/json",
                 "Accept: application/json")
    @POST("v1/visits")
    fun createVisit(@Body visitRequest: VisitRequest): Single<VisitResponse>

    @Headers("Content-Type: application/json",
                 "Accept: application/json")
    @POST("v1/availability")
    fun getTimeAvailability(@Body availableTimesRequest: AvailableTimesRequest): Single<JsonObject>

    @Headers("Accept: */*")
    @GET("v1/visits/{id}")
    fun getVisitById(@Path("id") id: String): Single<Visit>

    @Headers("Accept: */*")
    @GET("v1/addresses/{id}")
    fun getAddressById(@Path("id") id: String): Single<Address>

    @Headers("Content-Type: application/json",
                 "Accept: application/json")
    @POST("v1/addresses")
    fun createAddress(@Body addressRequest: CreateAddressRequest): Single<AddressResponse>
}