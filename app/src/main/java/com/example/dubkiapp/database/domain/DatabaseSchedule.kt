package com.example.dubkiapp.database.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dubkiapp.domain.Bus
import java.util.Calendar

@Entity(tableName = "databaseschedule")
data class DatabaseSchedule(
    @PrimaryKey
    val id: Int? = null,
    val day: String? = null,
    val direction: String? = null,
    val station: String? = null,
    val dayTimeString: String? = null,
    val dayTime: Long? = null
)

fun DatabaseSchedule.asDomainWithTime(): Bus {
    return Bus(
        inTime = (dayTime!! - (Calendar.getInstance().timeInMillis + 10800000 + 25200000) % 86400000) / 60000,
        station = when(station) {
            "odn" -> "Одинцово"
            "slv" -> "Славянский б-р"
            else -> "Молодежная"
        },
        dayTimeString = dayTimeString
    )
}

fun DatabaseSchedule.asDomain(): Bus {
    return Bus(
        inTime = null,
        station = when(station) {
            "odn" -> "Одинцово"
            "slv" -> "Славянский б-р"
            else -> "Молодежная"
        },
        dayTimeString = dayTimeString
    )
}

