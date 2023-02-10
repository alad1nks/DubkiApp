package com.app.dubkiapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.dubkiapp.R
import com.app.dubkiapp.ui.model.Bus
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
    val inTime = (dayTime!! - (Calendar.getInstance().timeInMillis + 10800000) % 86400000) / 60000
    val inTimeMinutes = kotlin.math.abs(inTime % 60)
    val inTimeHours = kotlin.math.abs(inTime / 60)
    val dayTimeStyleResId: Int?
    val inTimeStringResId: Int?
    val inTimeStyleResId: Int?
    val inTimeDrawableResId: Int?
    val stationStringResId: Int?
    val stationStyleResId: Int?
    var dayTimeGone:Int? = null
    var stationGone: Int? = null

    if (inTime / 60 > 0) {
        inTimeStringResId = R.string.coming
        inTimeStyleResId = R.style.TextAppearance_Medium_Intime
        inTimeDrawableResId = R.drawable.in_time_bus_common_bg
    } else if (inTime > 30) {
        inTimeStringResId = R.string.coming_soon
        inTimeStyleResId = R.style.TextAppearance_Medium_Intime
        inTimeDrawableResId = R.drawable.in_time_bus_common_bg
    } else if (inTime >= 0) {
        inTimeStringResId = R.string.coming_soon
        inTimeStyleResId = R.style.TextAppearance_Medium_Intime_Soon
        inTimeDrawableResId = R.drawable.in_time_bus_soon_bg
    } else if (inTime / 60 < 0) {
        inTimeStringResId = R.string.gone
        inTimeStyleResId = R.style.TextAppearance_Medium_InTime_Gone
        inTimeDrawableResId = R.drawable.in_time_bus_common_bg
        dayTimeGone = R.style.TextAppearance_Medium_DayTime_Gone
        stationGone = R.style.TextAppearance_Medium_Station_Gone
    } else {
        inTimeStringResId = R.string.gone_soon
        inTimeStyleResId = R.style.TextAppearance_Medium_InTime_Gone
        inTimeDrawableResId = R.drawable.in_time_bus_common_bg
        dayTimeGone = R.style.TextAppearance_Medium_DayTime_Gone
        stationGone = R.style.TextAppearance_Medium_Station_Gone
    }

    when (station!!) {
        "odn" -> {
            dayTimeStyleResId = dayTimeGone ?: R.style.TextAppearance_Medium_DayTime_Odintsovo
            stationStringResId = R.string.odintsovo
            stationStyleResId = stationGone ?: R.style.TextAppearance_Medium_Station_Odintsovo
        }
        "slv" -> {
            dayTimeStyleResId = dayTimeGone ?: R.style.TextAppearance_Medium_DayTime_Slavyanka
            stationStringResId = R.string.slavyanka
            stationStyleResId = stationGone ?: R.style.TextAppearance_Medium_Station_Slavyanka
        }
        else -> {
            dayTimeStyleResId = dayTimeGone ?: R.style.TextAppearance_Medium_DayTime_Molodyozhnaya
            stationStringResId = R.string.molodezhnaya
            stationStyleResId = stationGone ?: R.style.TextAppearance_Medium_Station_Molodyozhnaya
        }
    }

    return Bus(
        id = id!!,
        inTime = inTime,
        inTimeMinutes = inTimeMinutes.toInt(),
        inTimeHours = inTimeHours.toInt(),
        dayTimeString = dayTimeString!!,
        dayTimeStyleResId = dayTimeStyleResId,
        inTimeStringResId = inTimeStringResId,
        inTimeStyleResId = inTimeStyleResId,
        inTimeDrawableResId = inTimeDrawableResId,
        stationStringResId = stationStringResId,
        stationStyleResId = stationStyleResId
    )

}

fun DatabaseSchedule.asDomain(): Bus {

    val dayTimeStyleResId: Int?
    val stationStringResId: Int?
    val stationStyleResId: Int?

    when (station!!) {
        "odn" -> {
            dayTimeStyleResId = R.style.TextAppearance_Medium_DayTime_Odintsovo
            stationStringResId = R.string.odintsovo
            stationStyleResId = R.style.TextAppearance_Medium_Station_Odintsovo
        }
        "slv" -> {
            dayTimeStyleResId = R.style.TextAppearance_Medium_DayTime_Slavyanka
            stationStringResId = R.string.slavyanka
            stationStyleResId = R.style.TextAppearance_Medium_Station_Slavyanka
        }
        else -> {
            dayTimeStyleResId = R.style.TextAppearance_Medium_DayTime_Molodyozhnaya
            stationStringResId = R.string.molodezhnaya
            stationStyleResId = R.style.TextAppearance_Medium_Station_Molodyozhnaya
        }
    }

    return Bus(
        id = id!!,
        inTime = 0,
        dayTimeString = dayTimeString!!,
        dayTimeStyleResId = dayTimeStyleResId,
        stationStringResId = stationStringResId,
        stationStyleResId = stationStyleResId
    )
}

