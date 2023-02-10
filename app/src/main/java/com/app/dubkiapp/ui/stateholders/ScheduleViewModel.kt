package com.app.dubkiapp.ui.stateholders

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dubkiapp.domain.model.Day
import com.app.dubkiapp.domain.model.Station
import com.app.dubkiapp.domain.repositories.ScheduleRepository
import com.app.dubkiapp.ui.model.BusSchedule
import com.app.dubkiapp.ui.model.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

class ScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {

    private val _scheduleMoscow: MutableLiveData<BusSchedule> = MutableLiveData<BusSchedule>()
    val scheduleMoscow: LiveData<BusSchedule> = _scheduleMoscow

    private val _scheduleDubki: MutableLiveData<BusSchedule> = MutableLiveData<BusSchedule>()
    val scheduleDubki: LiveData<BusSchedule> = _scheduleDubki

    private val _day: MutableLiveData<Day> = MutableLiveData<Day>()
    val day: LiveData<Day> = _day

    private val _station: MutableLiveData<Station> = MutableLiveData<Station>()
    val station: LiveData<Station> = _station

    private val _status: MutableLiveData<Status> = MutableLiveData<Status>()
    val status: LiveData<Status> = _status

    private val today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)


    init {
        Log.d("init", "init")
        refreshSchedule()
    }
    private fun refreshSchedule() {
        viewModelScope.launch(Dispatchers.IO) {
            scheduleRepository.refreshScheduleFirebase()

            scheduleRepository.refreshScheduleMoscow(Day.TODAY, Station.ALL, today)
            _scheduleMoscow.postValue(BusSchedule(scheduleRepository.getScheduleMoscow(), 5))
            _scheduleDubki.postValue(BusSchedule(scheduleRepository.getScheduleMoscow(), 5))

            Log.d(TAG, "startuem")
        }
    }


    fun setDay(pos: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _day.postValue(
                when(pos) {
                    0 -> Day.TODAY
                    1 -> Day.TOMORROW
                    2 -> Day.WEEKDAY
                    3 -> Day.SATURDAY
                    else -> Day.SUNDAY
                }
            )
            Log.d("setDay", "setDay")
        }
    }

    fun setStation(pos: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _station.postValue(
                when(pos) {
                    0 -> Station.ALL
                    1 -> Station.ODINTSOVO
                    2 -> Station.MOLODEZHNAYA
                    else -> Station.SLAVYANKA
                }
            )
            Log.d("setStation", "setStation")
        }
    }

}