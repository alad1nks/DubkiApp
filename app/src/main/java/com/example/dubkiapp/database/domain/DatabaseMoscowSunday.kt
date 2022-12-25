package com.example.dubkiapp.database.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dubkiapp.domain.Bus

@Entity(tableName = "databasemoscowsunday")
data class DatabaseMoscowSunday(
    @PrimaryKey
    val id: Int? = null,
    val station: String? = null,
    val dayTime: Long? = null,
    val dayTimeFormat: String? = null
)

fun DatabaseMoscowSunday.asDomain(cur: Long): Bus {
    return Bus(
        inTime = (dayTime!! - (cur + 10800000) % 86400000) / 60000,
        station = when(station) {
            "Odintsovo" -> "Одинцово"
            "Slavyanka" -> "Славянский б-р"
            else -> "Молодежная"
        },
        dayTimeFormat = dayTimeFormat
    )
}

