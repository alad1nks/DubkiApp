package com.app.dubkiapp.domain.repositories

import com.app.dubkiapp.domain.model.Day
import com.app.dubkiapp.domain.model.Station
import com.app.dubkiapp.ui.model.Bus

interface ScheduleRepository {
    suspend fun refreshScheduleFirebase(): Boolean


    suspend fun refreshScheduleMoscow(day: Day, station: Station, today: Int)
    suspend fun refreshScheduleDubki(day: Day, station: Station, today: Int)


    suspend fun getNextMoscowBus(): Int
    suspend fun getScheduleMoscow(): List<Bus>
    suspend fun getNextDubkiBus(): Int
    suspend fun getScheduleDubki(): List<Bus>
}