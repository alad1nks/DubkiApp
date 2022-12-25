package com.example.dubkiapp.repositories

import com.example.dubkiapp.domain.Bus

interface ScheduleRepository {
    suspend fun refreshScheduleFirebase()
    suspend fun refreshScheduleMoscowDatabase()
    suspend fun getNextMoscowBus(): Int
    suspend fun getScheduleMoscow(): List<Bus>



    suspend fun refreshScheduleDubkiDatabase()
    suspend fun getNextDubkiBus(): Int
    suspend fun getScheduleDubki(): List<Bus>
}