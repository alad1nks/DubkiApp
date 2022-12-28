package com.example.dubkiapp.ui.schedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dubkiapp.domain.Bus
import com.example.dubkiapp.repositories.ScheduleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {
    val scheduleMoscow = MutableLiveData<Pair<List<Bus>, Int>>()
    val scheduleDubki = MutableLiveData<Pair<List<Bus>, Int>>()


    fun getSchedule(day: String, station: String) {
        viewModelScope.launch(Dispatchers.IO) {
            scheduleRepository.refreshScheduleFirebase()

            scheduleRepository.refreshScheduleMoscowDatabase(day, station)
            scheduleMoscow.postValue(Pair(scheduleRepository.getScheduleMoscow(), scheduleRepository.getNextMoscowBus()))

            scheduleRepository.refreshScheduleDubkiDatabase(day, station)
            scheduleDubki.postValue(Pair(scheduleRepository.getScheduleDubki(), scheduleRepository.getNextDubkiBus()))
        }
    }

}