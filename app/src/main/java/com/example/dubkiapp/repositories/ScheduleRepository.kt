package com.example.dubkiapp.repositories

import com.example.dubkiapp.domain.Bus

interface ScheduleRepository {
    suspend fun refreshScheduleFirebase()


    suspend fun refreshScheduleTodayMoscowAll()
    suspend fun refreshScheduleTodayMoscowOdintsovo()
    suspend fun refreshScheduleTodayMoscowSlavyanka()
    suspend fun refreshScheduleTodayMoscowMolodezhnaya()

    suspend fun refreshScheduleWeekdayMoscowAll()
    suspend fun refreshScheduleWeekdayMoscowOdintsovo()
    suspend fun refreshScheduleWeekdayMoscowSlavyanka()
    suspend fun refreshScheduleWeekdayMoscowMolodezhnaya()

    suspend fun refreshScheduleSaturdayMoscowAll()
    suspend fun refreshScheduleSaturdayMoscowOdintsovo()
    suspend fun refreshScheduleSaturdayMoscowSlavyanka()
    suspend fun refreshScheduleSaturdayMoscowMolodezhnaya()

    suspend fun refreshScheduleSundayMoscowAll()
    suspend fun refreshScheduleSundayMoscowOdintsovo()
    suspend fun refreshScheduleSundayMoscowSlavyanka()
    suspend fun refreshScheduleSundayMoscowMolodezhnaya()


    suspend fun refreshScheduleTodayDubkiAll()
    suspend fun refreshScheduleTodayDubkiOdintsovo()
    suspend fun refreshScheduleTodayDubkiSlavyanka()
    suspend fun refreshScheduleTodayDubkiMolodezhnaya()

    suspend fun refreshScheduleWeekdayDubkiAll()
    suspend fun refreshScheduleWeekdayDubkiOdintsovo()
    suspend fun refreshScheduleWeekdayDubkiSlavyanka()
    suspend fun refreshScheduleWeekdayDubkiMolodezhnaya()

    suspend fun refreshScheduleSaturdayDubkiAll()
    suspend fun refreshScheduleSaturdayDubkiOdintsovo()
    suspend fun refreshScheduleSaturdayDubkiSlavyanka()
    suspend fun refreshScheduleSaturdayDubkiMolodezhnaya()

    suspend fun refreshScheduleSundayDubkiAll()
    suspend fun refreshScheduleSundayDubkiOdintsovo()
    suspend fun refreshScheduleSundayDubkiSlavyanka()
    suspend fun refreshScheduleSundayDubkiMolodezhnaya()


    suspend fun getNextMoscowBus(): Int
    suspend fun getScheduleMoscow(): List<Bus>
    suspend fun getNextDubkiBus(): Int
    suspend fun getScheduleDubki(): List<Bus>
}