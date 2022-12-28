package com.example.dubkiapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dubkiapp.database.domain.DatabaseSchedule


@Database(
    entities = [
        DatabaseSchedule::class
               ],
    version = 2,
    exportSchema = false
)
abstract class ScheduleDatabase: RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
}