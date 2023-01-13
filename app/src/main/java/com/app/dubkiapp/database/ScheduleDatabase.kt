package com.app.dubkiapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.dubkiapp.database.domain.DatabaseSchedule


@Database(
    entities = [
        DatabaseSchedule::class
               ],
    version = 3,
    exportSchema = false
)
abstract class ScheduleDatabase: RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
}