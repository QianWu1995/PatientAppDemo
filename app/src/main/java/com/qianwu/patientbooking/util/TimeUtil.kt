package com.qianwu.patientbooking.util

import com.google.gson.JsonObject
import com.qianwu.patientbooking.domain.entity.AvailableTimes

object TimeUtil {

    fun jsonToAvailableTimes(json: JsonObject): AvailableTimes {
        val map = mutableMapOf<String, MutableList<String>>().withDefault { mutableListOf() }
        for (key in json.keySet()){
            for (element in json.getAsJsonArray(key)){
                if(map[key] == null){
                    map[key] = mutableListOf()
                }
                map[key]?.add(element.asString)
            }
        }

        return AvailableTimes(map)
    }

    fun stringToTimeFormat(){
        //TODO
    }
}