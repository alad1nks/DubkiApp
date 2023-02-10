package com.app.dubkiapp.data.api

import com.app.dubkiapp.data.model.DatabaseSchedule


data class BusResponse(
    val id: Int? = null,
    val day: String? = null,
    val direction: String? = null,
    val station: String? = null,
    val dayTimeString: String? = null,
    val dayTime: Long? = null
)

fun BusResponse.asDatabase(): DatabaseSchedule {
    return DatabaseSchedule(
        id = id,
        day = day,
        direction = direction,
        station = station,
        dayTimeString = dayTimeString,
        dayTime = dayTime
    )
}