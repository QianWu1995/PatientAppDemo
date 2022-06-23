package com.qianwu.patientbooking.domain

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun stringToList(value: String): List<String> = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun listToString(list: List<String>): String? = Gson().toJson(list)

}