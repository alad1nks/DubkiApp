package com.example.dubkiapp.repositories

import com.example.dubkiapp.domain.Bus

interface ScheduleRepository {
    suspend fun refreshScheduleFirebase()


    suspend fun refreshScheduleMoscowDatabase(day: String, station: String)
    suspend fun getNextMoscowBus(): Int
    suspend fun getScheduleMoscow(): List<Bus>



    suspend fun refreshScheduleDubkiDatabase(day: String, station: String)
    suspend fun getNextDubkiBus(): Int
    suspend fun getScheduleDubki(): List<Bus>
}