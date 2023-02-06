package com.app.dubkiapp.ui.schedule

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dubkiapp.domain.Bus
import com.app.dubkiapp.domain.Day
import com.app.dubkiapp.domain.Station
import com.app.dubkiapp.repositories.ScheduleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {

    val scheduleMoscow = MutableLiveData<List<Bus>>()
    val scheduleDubki = MutableLiveData<List<Bus>>()

    private val day = MutableLiveData<Day>()
    private val station = MutableLiveData<Station>()

    init {
        refreshSchedule()
    }
    private fun refreshSchedule() {
        viewModelScope.launch(Dispatchers.IO) {
//            while (true) {
            scheduleRepository.refreshScheduleFirebase()

            scheduleRepository.refreshScheduleTodayMoscowAll()
            scheduleMoscow.postValue(scheduleRepository.getScheduleMoscow())
            Log.d("karamba", "${scheduleMoscow.value}")

//                scheduleRepository.refreshScheduleTodayDubkiAll()
//                scheduleTodayDubkiAll.postValue(Pair(scheduleRepository.getScheduleDubki(), scheduleRepository.getNextDubkiBus()))
//
//
//                scheduleRepository.refreshScheduleTodayMoscowOdintsovo()
//                scheduleTodayMoscowOdintsovo.postValue(Pair(scheduleRepository.getScheduleMoscow(), scheduleRepository.getNextMoscowBus()))
//
//                scheduleRepository.refreshScheduleTodayDubkiOdintsovo()
//                scheduleTodayDubkiOdintsovo.postValue(Pair(scheduleRepository.getScheduleDubki(), scheduleRepository.getNextDubkiBus()))
//
//
//                scheduleRepository.refreshScheduleTodayMoscowSlavyanka()
//                scheduleTodayMoscowSlavyanka.postValue(Pair(scheduleRepository.getScheduleMoscow(), scheduleRepository.getNextMoscowBus()))
//
//                scheduleRepository.refreshScheduleTodayDubkiSlavyanka()
//                scheduleTodayDubkiSlavyanka.postValue(Pair(scheduleRepository.getScheduleDubki(), scheduleRepository.getNextDubkiBus()))
//
//
//                scheduleRepository.refreshScheduleTodayMoscowMolodezhnaya()
//                scheduleTodayMoscowMolodezhnaya.postValue(Pair(scheduleRepository.getScheduleMoscow(), scheduleRepository.getNextMoscowBus()))
//
//                scheduleRepository.refreshScheduleTodayDubkiMolodezhnaya()
//                scheduleTodayDubkiMolodezhnaya.postValue(Pair(scheduleRepository.getScheduleDubki(), scheduleRepository.getNextDubkiBus()))
//
//
//                scheduleRepository.refreshScheduleWeekdayMoscowAll()
//                scheduleWeekdayMoscowAll.postValue(scheduleRepository.getScheduleMoscow())
//
//                scheduleRepository.refreshScheduleWeekdayDubkiAll()
//                scheduleWeekdayDubkiAll.postValue(scheduleRepository.getScheduleDubki())
//
//
//                scheduleRepository.refreshScheduleWeekdayMoscowOdintsovo()
//                scheduleWeekdayMoscowOdintsovo.postValue(scheduleRepository.getScheduleMoscow())
//
//                scheduleRepository.refreshScheduleWeekdayDubkiOdintsovo()
//                scheduleWeekdayDubkiOdintsovo.postValue(scheduleRepository.getScheduleDubki())
//
//
//                scheduleRepository.refreshScheduleWeekdayMoscowSlavyanka()
//                scheduleWeekdayMoscowSlavyanka.postValue(scheduleRepository.getScheduleMoscow())
//
//                scheduleRepository.refreshScheduleWeekdayDubkiSlavyanka()
//                scheduleWeekdayDubkiSlavyanka.postValue(scheduleRepository.getScheduleDubki())
//
//
//                scheduleRepository.refreshScheduleWeekdayMoscowMolodezhnaya()
//                scheduleWeekdayMoscowMolodezhnaya.postValue(scheduleRepository.getScheduleMoscow())
//
//                scheduleRepository.refreshScheduleWeekdayDubkiMolodezhnaya()
//                scheduleWeekdayDubkiMolodezhnaya.postValue(scheduleRepository.getScheduleDubki())
//
//
//                scheduleRepository.refreshScheduleSaturdayMoscowAll()
//                scheduleSaturdayMoscowAll.postValue(scheduleRepository.getScheduleMoscow())
//
//                scheduleRepository.refreshScheduleSaturdayDubkiAll()
//                scheduleSaturdayDubkiAll.postValue(scheduleRepository.getScheduleDubki())
//
//
//                scheduleRepository.refreshScheduleSaturdayMoscowOdintsovo()
//                scheduleSaturdayMoscowOdintsovo.postValue(scheduleRepository.getScheduleMoscow())
//
//                scheduleRepository.refreshScheduleSaturdayDubkiOdintsovo()
//                scheduleSaturdayDubkiOdintsovo.postValue(scheduleRepository.getScheduleDubki())
//
//
//                scheduleRepository.refreshScheduleSaturdayMoscowSlavyanka()
//                scheduleSaturdayMoscowSlavyanka.postValue(scheduleRepository.getScheduleMoscow())
//
//                scheduleRepository.refreshScheduleSaturdayDubkiSlavyanka()
//                scheduleSaturdayDubkiSlavyanka.postValue(scheduleRepository.getScheduleDubki())
//
//
//                scheduleRepository.refreshScheduleSaturdayMoscowMolodezhnaya()
//                scheduleSaturdayMoscowMolodezhnaya.postValue(scheduleRepository.getScheduleMoscow())
//
//                scheduleRepository.refreshScheduleSaturdayDubkiMolodezhnaya()
//                scheduleSaturdayDubkiMolodezhnaya.postValue(scheduleRepository.getScheduleDubki())
//
//
//                scheduleRepository.refreshScheduleSundayMoscowAll()
//                scheduleSundayMoscowAll.postValue(scheduleRepository.getScheduleMoscow())
//
//                scheduleRepository.refreshScheduleSundayDubkiAll()
//                scheduleSundayDubkiAll.postValue(scheduleRepository.getScheduleDubki())
//
//
//                scheduleRepository.refreshScheduleSundayMoscowOdintsovo()
//                scheduleSundayMoscowOdintsovo.postValue(scheduleRepository.getScheduleMoscow())
//
//                scheduleRepository.refreshScheduleSundayDubkiOdintsovo()
//                scheduleSundayDubkiOdintsovo.postValue(scheduleRepository.getScheduleDubki())
//
//
//                scheduleRepository.refreshScheduleSundayMoscowSlavyanka()
//                scheduleSundayMoscowSlavyanka.postValue(scheduleRepository.getScheduleMoscow())
//
//                scheduleRepository.refreshScheduleSundayDubkiSlavyanka()
//                scheduleSundayDubkiSlavyanka.postValue(scheduleRepository.getScheduleDubki())
//
//
//                scheduleRepository.refreshScheduleSundayMoscowMolodezhnaya()
//                scheduleSundayMoscowMolodezhnaya.postValue(scheduleRepository.getScheduleMoscow())
//
//                scheduleRepository.refreshScheduleSundayDubkiMolodezhnaya()
//                scheduleSundayDubkiMolodezhnaya.postValue(scheduleRepository.getScheduleDubki())
//
//
//                scheduleRepository.refreshScheduleTomorrowMoscowAll()
//                scheduleTomorrowMoscowAll.postValue(scheduleRepository.getScheduleMoscow())
//
//
//                scheduleRepository.refreshScheduleTomorrowDubkiAll()
//                scheduleTomorrowDubkiAll.postValue(scheduleRepository.getScheduleDubki())
//
//
//                scheduleRepository.refreshScheduleTomorrowMoscowOdintsovo()
//                scheduleTomorrowMoscowOdintsovo.postValue(scheduleRepository.getScheduleMoscow())
//
//                scheduleRepository.refreshScheduleTomorrowDubkiOdintsovo()
//                scheduleTomorrowDubkiOdintsovo.postValue(scheduleRepository.getScheduleDubki())
//
//
//                scheduleRepository.refreshScheduleTomorrowMoscowSlavyanka()
//                scheduleTomorrowMoscowSlavyanka.postValue(scheduleRepository.getScheduleMoscow())
//
//                scheduleRepository.refreshScheduleTomorrowDubkiSlavyanka()
//                scheduleTomorrowDubkiSlavyanka.postValue(scheduleRepository.getScheduleDubki())
//
//
//                scheduleRepository.refreshScheduleTomorrowMoscowMolodezhnaya()
//                scheduleTomorrowMoscowMolodezhnaya.postValue(scheduleRepository.getScheduleMoscow())
//
//                scheduleRepository.refreshScheduleTomorrowDubkiMolodezhnaya()
//                scheduleTomorrowDubkiMolodezhnaya.postValue(scheduleRepository.getScheduleDubki())
                Log.d(TAG, "startuem")
//            }
        }
    }


    fun setDay() {

    }

    fun setStation() {

    }

}