package com.qianwu.patientbooking

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.qianwu.patientbooking.util.TimeUtil
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TimeUtilTests {
    val inputJsonString = """{
        "2021-03-01": [
        "2021-03-01T18:00:00+0000"
        ],
        "2021-03-02": [
        "2021-03-02T17:00:00+0000"
        ],
        "2021-03-03": [
        "2021-03-03T18:00:00+0000",
        "2021-03-03T19:00:00+0000"
        ],
        "2021-03-04": []
    }"""

    lateinit var inputJson: JsonObject

    @Before
    fun `set up`(){
        inputJson = JsonParser.parseString(inputJsonString).asJsonObject
    }

    @Test
    fun `test example input json null`(){
        val result = TimeUtil.jsonToAvailableTimes(inputJson)
        assertEquals(null, result.availablityMap["2021-03-04"])
    }

    @Test
    fun `test example input json with non null result`(){
        val result = TimeUtil.jsonToAvailableTimes(inputJson)
        assertEquals(listOf<String?>("2021-03-01T18:00:00+0000"), result.availablityMap["2021-03-01"])
    }

}