package com.example.dubkiapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dubkiapp.database.domain.DatabaseSchedule

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM databaseschedule WHERE day = 'wkd' AND direction = 'msk' ORDER BY dayTime")
    fun getScheduleWeekdayMoscowAll(): List<DatabaseSchedule>

    @Query("SELECT * FROM databaseschedule WHERE day = 'std' AND direction = 'msk' ORDER BY dayTime")
    fun getScheduleSaturdayMoscowAll(): List<DatabaseSchedule>

    @Query("SELECT * FROM databaseschedule WHERE day = 'snd' AND direction = 'msk' ORDER BY dayTime")
    fun getScheduleSundayMoscowAll(): List<DatabaseSchedule>


    @Query("SELECT * FROM databaseschedule WHERE day = 'wkd' AND direction = 'dbk' ORDER BY dayTime")
    fun getScheduleWeekdayDubkiAll(): List<DatabaseSchedule>

    @Query("SELECT * FROM databaseschedule WHERE day = 'std' AND direction = 'dbk' ORDER BY dayTime")
    fun getScheduleSaturdayDubkiAll(): List<DatabaseSchedule>

    @Query("SELECT * FROM databaseschedule WHERE day = 'snd' AND direction = 'dbk' ORDER BY dayTime")
    fun getScheduleSundayDubkiAll(): List<DatabaseSchedule>


    @Query("SELECT * FROM databaseschedule WHERE day = 'wkd' AND direction = 'msk' AND station = 'odn' ORDER BY dayTime")
    fun getScheduleWeekdayMoscowOdn(): List<DatabaseSchedule>

    @Query("SELECT * FROM databaseschedule WHERE day = 'std' AND direction = 'msk' AND station = 'odn' ORDER BY dayTime")
    fun getScheduleSaturdayMoscowOdn(): List<DatabaseSchedule>

    @Query("SELECT * FROM databaseschedule WHERE day = 'snd' AND direction = 'msk' AND station = 'odn' ORDER BY dayTime")
    fun getScheduleSundayMoscowOdn(): List<DatabaseSchedule>


    @Query("SELECT * FROM databaseschedule WHERE day = 'wkd' AND direction = 'dbk' AND station = 'odn' ORDER BY dayTime")
    fun getScheduleWeekdayDubkiOdn(): List<DatabaseSchedule>

    @Query("SELECT * FROM databaseschedule WHERE day = 'std' AND direction = 'dbk' AND station = 'odn' ORDER BY dayTime")
    fun getScheduleSaturdayDubkiOdn(): List<DatabaseSchedule>

    @Query("SELECT * FROM databaseschedule WHERE day = 'snd' AND direction = 'dbk' AND station = 'odn' ORDER BY dayTime")
    fun getScheduleSundayDubkiOdn(): List<DatabaseSchedule>


    @Query("SELECT * FROM databaseschedule WHERE day = 'wkd' AND direction = 'msk' AND station = 'slv' ORDER BY dayTime")
    fun getScheduleWeekdayMoscowSlv(): List<DatabaseSchedule>

    @Query("SELECT * FROM databaseschedule WHERE day = 'std' AND direction = 'msk' AND station = 'slv' ORDER BY dayTime")
    fun getScheduleSaturdayMoscowSlv(): List<DatabaseSchedule>

    @Query("SELECT * FROM databaseschedule WHERE day = 'snd' AND direction = 'msk' AND station = 'slv' ORDER BY dayTime")
    fun getScheduleSundayMoscowSlv(): List<DatabaseSchedule>


    @Query("SELECT * FROM databaseschedule WHERE day = 'wkd' AND direction = 'dbk' AND station = 'slv' ORDER BY dayTime")
    fun getScheduleWeekdayDubkiSlv(): List<DatabaseSchedule>

    @Query("SELECT * FROM databaseschedule WHERE day = 'std' AND direction = 'dbk' AND station = 'slv' ORDER BY dayTime")
    fun getScheduleSaturdayDubkiSlv(): List<DatabaseSchedule>

    @Query("SELECT * FROM databaseschedule WHERE day = 'snd' AND direction = 'dbk' AND station = 'slv' ORDER BY dayTime")
    fun getScheduleSundayDubkiSlv(): List<DatabaseSchedule>


    @Query("SELECT * FROM databaseschedule WHERE day = 'wkd' AND direction = 'msk' AND station = 'mld' ORDER BY dayTime")
    fun getScheduleWeekdayMoscowMld(): List<DatabaseSchedule>

    @Query("SELECT * FROM databaseschedule WHERE day = 'std' AND direction = 'msk' AND station = 'mld' ORDER BY dayTime")
    fun getScheduleSaturdayMoscowMld(): List<DatabaseSchedule>

    @Query("SELECT * FROM databaseschedule WHERE day = 'snd' AND direction = 'msk' AND station = 'mld' ORDER BY dayTime")
    fun getScheduleSundayMoscowMld(): List<DatabaseSchedule>


    @Query("SELECT * FROM databaseschedule WHERE day = 'wkd' AND direction = 'dbk' AND station = 'mld' ORDER BY dayTime")
    fun getScheduleWeekdayDubkiMld(): List<DatabaseSchedule>

    @Query("SELECT * FROM databaseschedule WHERE day = 'std' AND direction = 'dbk' AND station = 'mld' ORDER BY dayTime")
    fun getScheduleSaturdayDubkiMld(): List<DatabaseSchedule>

    @Query("SELECT * FROM databaseschedule WHERE day = 'snd' AND direction = 'dbk' AND station = 'mld' ORDER BY dayTime")
    fun getScheduleSundayDubkiMld(): List<DatabaseSchedule>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSchedule(schedule: List<DatabaseSchedule>)

    @Query("DELETE FROM databaseschedule")
    fun clearSchedule()
}