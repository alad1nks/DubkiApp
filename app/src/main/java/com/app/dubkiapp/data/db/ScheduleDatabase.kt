package com.app.dubkiapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.dubkiapp.data.model.DatabaseSchedule


@Database(
    entities = [
        DatabaseSchedule::class
               ],
    version = 6,
    exportSchema = false
)
abstract class ScheduleDatabase: RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
}