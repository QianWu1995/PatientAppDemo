package com.qianwu.patientbooking.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
/*
 Robin Qian Wu
 */
@Entity(tableName = "patients")
data class Patient(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @SerializedName("first_name")
    @ColumnInfo(name = "first_name")
    var firstName: String? = null,

    @SerializedName("last_name")
    @ColumnInfo(name = "last_name")
    var lastName: String? = null,

    @SerializedName("dob")
    @ColumnInfo(name = "dob")
    var dob: String? = null,

    @SerializedName("email")
    @ColumnInfo(name = "email")
    var email: String? = null,

    @SerializedName("phone")
    @ColumnInfo(name = "phone")
    var phone: String? = null,

    @SerializedName("preferred_language")
    @ColumnInfo(name = "preferred_language")
    var preferredLanguage: String = "en"

){
    fun copy(newId: String): Patient{
        return Patient(newId, firstName, lastName,dob, email, phone, preferredLanguage)
    }
}

data class PatientResponse(
    @SerializedName("id") val id: String
)