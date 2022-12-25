package com.example.dubkiapp.network

import com.example.dubkiapp.database.domain.DatabaseDubkiSaturday
import com.example.dubkiapp.database.domain.DatabaseDubkiSunday
import com.example.dubkiapp.database.domain.DatabaseDubkiWeekday
import com.example.dubkiapp.database.domain.DatabaseMoscowSaturday
import com.example.dubkiapp.database.domain.DatabaseMoscowSunday
import com.example.dubkiapp.database.domain.DatabaseMoscowWeekday
import com.example.dubkiapp.domain.Bus

data class BusResponse(
    val id: Int? = null,
    val direction: String? = null,
    val dayTime: Long? = null,
    val dayTimeFormat: String? = null
)

fun BusResponse.asDatabaseMoscowWeekday(): DatabaseMoscowWeekday {
    return DatabaseMoscowWeekday(
        id = id,
        station = direction,
        dayTime = dayTime,
        dayTimeFormat = dayTimeFormat
    )
}

fun BusResponse.asDatabaseMoscowSaturday(): DatabaseMoscowSaturday {
    return DatabaseMoscowSaturday(
        id = id,
        station = direction,
        dayTime = dayTime,
        dayTimeFormat = dayTimeFormat
    )
}

fun BusResponse.asDatabaseMoscowSunday(): DatabaseMoscowSunday {
    return DatabaseMoscowSunday(
        id = id,
        station = direction,
        dayTime = dayTime,
        dayTimeFormat = dayTimeFormat
    )
}



fun BusResponse.asDatabaseDubkiWeekday(): DatabaseDubkiWeekday {
    return DatabaseDubkiWeekday(
        id = id,
        station = direction,
        dayTime = dayTime,
        dayTimeFormat = dayTimeFormat
    )
}

fun BusResponse.asDatabaseDubkiSaturday(): DatabaseDubkiSaturday {
    return DatabaseDubkiSaturday(
        id = id,
        station = direction,
        dayTime = dayTime,
        dayTimeFormat = dayTimeFormat
    )
}

fun BusResponse.asDatabaseDubkiSunday(): DatabaseDubkiSunday {
    return DatabaseDubkiSunday(
        id = id,
        station = direction,
        dayTime = dayTime,
        dayTimeFormat = dayTimeFormat
    )
}