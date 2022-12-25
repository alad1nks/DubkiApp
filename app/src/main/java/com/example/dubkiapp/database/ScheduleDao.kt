package com.example.dubkiapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dubkiapp.database.domain.DatabaseDubkiSaturday
import com.example.dubkiapp.database.domain.DatabaseDubkiSunday
import com.example.dubkiapp.database.domain.DatabaseDubkiWeekday
import com.example.dubkiapp.database.domain.DatabaseMoscowSaturday
import com.example.dubkiapp.database.domain.DatabaseMoscowSunday
import com.example.dubkiapp.database.domain.DatabaseMoscowWeekday

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM databasemoscowweekday")
    fun getScheduleMoscowWeekday(): List<DatabaseMoscowWeekday>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScheduleMoscowWeekday(schedule: List<DatabaseMoscowWeekday>)

    @Query("DELETE FROM databasemoscowweekday")
    fun clearScheduleMoscowWeekday()


    @Query("SELECT * FROM databasemoscowsaturday")
    fun getScheduleMoscowSaturday(): List<DatabaseMoscowSaturday>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertScheduleMoscowSaturday(schedule: List<DatabaseMoscowSaturday>)

    @Query("DELETE FROM databasemoscowsaturday")
    fun clearScheduleMoscowSaturday()


    @Query("SELECT * FROM databasemoscowsunday")
    fun getScheduleMoscowSunday(): List<DatabaseMoscowSunday>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertScheduleMoscowSunday(schedule: List<DatabaseMoscowSunday>)

    @Query("DELETE FROM databasemoscowsunday")
    fun clearScheduleMoscowSunday()



    @Query("SELECT * FROM databasedubkiweekday")
    fun getScheduleDubkiWeekday(): List<DatabaseDubkiWeekday>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertScheduleDubkiWeekday(schedule: List<DatabaseDubkiWeekday>)

    @Query("DELETE FROM databasedubkiweekday")
    fun clearScheduleDubkiWeekday()


    @Query("SELECT * FROM databasedubkisaturday")
    fun getScheduleDubkiSaturday(): List<DatabaseDubkiSaturday>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertScheduleDubkiSaturday(schedule: List<DatabaseDubkiSaturday>)

    @Query("DELETE FROM databasedubkisaturday")
    fun clearScheduleDubkiSaturday()


    @Query("SELECT * FROM databasedubkisunday")
    fun getScheduleDubkiSunday(): List<DatabaseDubkiSunday>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertScheduleDubkiSunday(schedule: List<DatabaseDubkiSunday>)

    @Query("DELETE FROM databasedubkisunday")
    fun clearScheduleDubkiSunday()
}