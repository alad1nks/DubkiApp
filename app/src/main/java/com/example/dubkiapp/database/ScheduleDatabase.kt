package com.example.dubkiapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dubkiapp.database.domain.DatabaseDubkiSaturday
import com.example.dubkiapp.database.domain.DatabaseDubkiSunday
import com.example.dubkiapp.database.domain.DatabaseDubkiWeekday
import com.example.dubkiapp.database.domain.DatabaseMoscowSaturday
import com.example.dubkiapp.database.domain.DatabaseMoscowSunday
import com.example.dubkiapp.database.domain.DatabaseMoscowWeekday


@Database(
    entities = [
        DatabaseMoscowWeekday::class,
        DatabaseMoscowSaturday::class,
        DatabaseMoscowSunday::class,
        DatabaseDubkiWeekday::class,
        DatabaseDubkiSaturday::class,
        DatabaseDubkiSunday::class
               ],
    version = 1,
    exportSchema = false
)
abstract class ScheduleDatabase: RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
}