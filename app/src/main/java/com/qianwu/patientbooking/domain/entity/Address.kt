package com.qianwu.patientbooking.domain.entity

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("address1")
    val address1: String,
    @SerializedName("address2")
    val address2: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("zip_code")
    val zipCode: String
)

data class CreateAddressRequest(
    @SerializedName("address")
    val address: Address,
    @SerializedName("patient_id")
    val patientId: String
)

data class AddressResponse(
    @SerializedName("id")
    val patientId: String
)
