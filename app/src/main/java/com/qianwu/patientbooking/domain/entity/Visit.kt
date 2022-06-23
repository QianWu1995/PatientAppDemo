package com.qianwu.patientbooking.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "visits")
data class Visit(
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: String,

    @SerializedName("patient_id")
    @ColumnInfo(name = "patient_id")
    var patientId: String? = null,

    @SerializedName("address_id")
    @ColumnInfo(name = "address_id")
    var addressId: String? = null,

    @SerializedName("visit_datetime")
    @ColumnInfo(name = "visit_datetime")
    var visitDatetime: String? = null,

    @SerializedName("requisitioning_provider_id")
    @ColumnInfo(name = "requisitioning_provider_id")
    var requisitioningProviderId: String? = null,

    @SerializedName("qhp_notes")
    @ColumnInfo(name = "qhp_notes")
    var qhpNotes: String? = null,

    @SerializedName("service_ids")
    @ColumnInfo(name = "service_ids")
    val serviceIds: List<String>,

    @SerializedName("related_patient_ids")
    @ColumnInfo(name = "related_patient_ids")
    val relatedPatientIds: List<String>
)

data class VisitRequest(

    @SerializedName("patient_id") val patientId: String,
    @SerializedName("address_id") val addressId: String,
    @SerializedName("visit_datetime") val visitDatetime: String,
    @SerializedName("is_self_pay") val isSelfPay: Boolean,
    @SerializedName("service_ids") val serviceIds: List<String>,
    @SerializedName("requisitioning_provider_id") val requisitioningProviderId: String,
    @SerializedName("provider_notes") var providerNotes: String?

)

data class VisitResponse(
    @SerializedName("visit_id") val visitId: String,
    @SerializedName("status") val status: String,
    @SerializedName("link") val link: String
)