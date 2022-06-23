package com.qianwu.patientbooking.domain.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*
 Robin Qian Wu
 */

data class Service(

    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var id: String,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String? = null,

    @SerializedName("description")
    @ColumnInfo(name = "description")
    var description: String? = null,

    @SerializedName("duration")
    @ColumnInfo(name = "duration")
    var duration: Int? = null,

    @SerializedName("min_turnaround_time")
    @ColumnInfo(name = "min_turnaround_time")
    var minTurnaroundTime: Int? = null,

    @SerializedName("max_turnaround_time")
    @ColumnInfo(name = "max_turnaround_time")
    var maxTurnaroundTime: Int? = null,

    @SerializedName("price")
    @ColumnInfo(name = "price")
    var price: Int? = null,

    @SerializedName("cpt_codes")
    @ColumnInfo(name = "cpt_codes")
    val cptCodes: List<String>,

    @SerializedName("physician_requisition_required")
    @ColumnInfo(name = "physician_requisition_required")
    var physicianRequisitionRequired: Boolean? = null

) : Serializable