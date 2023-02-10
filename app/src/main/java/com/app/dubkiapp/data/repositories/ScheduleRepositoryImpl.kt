package com.app.dubkiapp.data.repositories

import android.content.ContentValues.TAG
import android.util.Log
import com.app.dubkiapp.data.db.ScheduleDatabase
import com.app.dubkiapp.data.model.asDomain
import com.app.dubkiapp.data.model.asDomainWithTime
import com.app.dubkiapp.ui.model.Bus
import com.app.dubkiapp.data.api.BusResponse
import com.app.dubkiapp.data.api.asDatabase
import com.app.dubkiapp.domain.model.Day
import com.app.dubkiapp.domain.model.Station
import com.app.dubkiapp.preferences.AppPreference
import com.app.dubkiapp.domain.repositories.ScheduleRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeoutOrNull
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

@Singleton
class ScheduleRepositoryImpl @Inject constructor(
    private val realtimeDatabase: FirebaseDatabase,
    private val database: ScheduleDatabase,
    private val appPreference: AppPreference
): ScheduleRepository {
    private var scheduleMoscow: List<Bus> = listOf()
    private var scheduleDubki: List<Bus> = listOf()
    private var revision: Int? = null

    override suspend fun refreshScheduleFirebase(): Boolean {
        revision = suspendCoroutineWithTimeout(2000L) {
            realtimeDatabase.reference.child("schedule").child("revision").get().addOnSuccessListener { result ->
                val response = result.getValue(Long::class.java)
                it.resume(response!!.toInt())
            }
        }

        if (revision == null) {
            return false
        } else if (revision!! != appPreference.getRevision()) {
            appPreference.setRevision(revision!!)
            refreshDatabase()
        }
        return true
    }

    override suspend fun refreshScheduleMoscow(day: Day, station: Station, today: Int) {
        when(day) {
            Day.TODAY -> {
                scheduleMoscow = database.scheduleDao().getScheduleMoscow("today", "odn").map { it.asDomainWithTime() }
            }

            else -> {}
        }
        scheduleMoscow = database.scheduleDao().getScheduleMoscow("wkd", "odn").map { it.asDomain() }
    }

    override suspend fun refreshScheduleDubki(day: Day, station: Station, today: Int) {
        TODO("Not yet implemented")
    }

//    override suspend fun refreshScheduleTodayMoscowAll() {
//        scheduleMoscow = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
//            1 -> database.scheduleDao().getScheduleSundayMoscowAll().map { it.asDomainWithTime() }
//            7 -> database.scheduleDao().getScheduleSaturdayMoscowAll().map { it.asDomainWithTime() }
//            else -> database.scheduleDao().getScheduleWeekdayMoscowAll().map { it.asDomainWithTime() }
//        }
//        Log.d(TAG, "schedule$scheduleMoscow")
//    }
//
//    override suspend fun refreshScheduleTodayMoscowOdintsovo() {
//        scheduleMoscow = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
//            1 -> database.scheduleDao().getScheduleSundayMoscowOdn().map { it.asDomainWithTime() }
//            7 -> database.scheduleDao().getScheduleSaturdayMoscowOdn().map { it.asDomainWithTime() }
//            else -> database.scheduleDao().getScheduleWeekdayMoscowOdn().map { it.asDomainWithTime() }
//        }
//    }
//
//    override suspend fun refreshScheduleTodayMoscowSlavyanka() {
//        scheduleMoscow = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
//            1 -> database.scheduleDao().getScheduleSundayMoscowSlv().map { it.asDomainWithTime() }
//            7 -> database.scheduleDao().getScheduleSaturdayMoscowSlv().map { it.asDomainWithTime() }
//            else -> database.scheduleDao().getScheduleWeekdayMoscowSlv().map { it.asDomainWithTime() }
//        }
//    }
//
//    override suspend fun refreshScheduleTodayMoscowMolodezhnaya() {
//        scheduleMoscow = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
//            1 -> database.scheduleDao().getScheduleSundayMoscowMld().map { it.asDomainWithTime() }
//            7 -> database.scheduleDao().getScheduleSaturdayMoscowMld().map { it.asDomainWithTime() }
//            else -> database.scheduleDao().getScheduleWeekdayMoscowMld().map { it.asDomainWithTime() }
//        }
//    }
//
//
//    override suspend fun refreshScheduleTomorrowMoscowAll() {
//        scheduleMoscow = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
//            7 -> database.scheduleDao().getScheduleSundayMoscowAll().map { it.asDomain() }
//            6 -> database.scheduleDao().getScheduleSaturdayMoscowAll().map { it.asDomain() }
//            else -> database.scheduleDao().getScheduleWeekdayMoscowAll().map { it.asDomain() }
//        }
//    }
//
//    override suspend fun refreshScheduleTomorrowMoscowOdintsovo() {
//        scheduleMoscow = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
//            7 -> database.scheduleDao().getScheduleSundayMoscowOdn().map { it.asDomain() }
//            6 -> database.scheduleDao().getScheduleSaturdayMoscowOdn().map { it.asDomain() }
//            else -> database.scheduleDao().getScheduleWeekdayMoscowOdn().map { it.asDomain() }
//        }
//    }
//
//    override suspend fun refreshScheduleTomorrowMoscowSlavyanka() {
//        scheduleMoscow = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
//            7 -> database.scheduleDao().getScheduleSundayMoscowSlv().map { it.asDomain() }
//            6 -> database.scheduleDao().getScheduleSaturdayMoscowSlv().map { it.asDomain() }
//            else -> database.scheduleDao().getScheduleWeekdayMoscowSlv().map { it.asDomain() }
//        }
//    }
//
//    override suspend fun refreshScheduleTomorrowMoscowMolodezhnaya() {
//        scheduleMoscow = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
//            7 -> database.scheduleDao().getScheduleSundayMoscowMld().map { it.asDomain() }
//            6 -> database.scheduleDao().getScheduleSaturdayMoscowMld().map { it.asDomain() }
//            else -> database.scheduleDao().getScheduleWeekdayMoscowMld().map { it.asDomain() }
//        }
//    }
//
//
//    override suspend fun refreshScheduleWeekdayMoscowAll() {
//        scheduleMoscow = database.scheduleDao().getScheduleWeekdayMoscowAll().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleWeekdayMoscowOdintsovo() {
//        scheduleMoscow = database.scheduleDao().getScheduleWeekdayMoscowOdn().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleWeekdayMoscowSlavyanka() {
//        scheduleMoscow = database.scheduleDao().getScheduleWeekdayMoscowSlv().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleWeekdayMoscowMolodezhnaya() {
//        scheduleMoscow = database.scheduleDao().getScheduleWeekdayMoscowMld().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleSaturdayMoscowAll() {
//        scheduleMoscow = database.scheduleDao().getScheduleSaturdayMoscowAll().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleSaturdayMoscowOdintsovo() {
//        scheduleMoscow = database.scheduleDao().getScheduleSaturdayMoscowOdn().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleSaturdayMoscowSlavyanka() {
//        scheduleMoscow = database.scheduleDao().getScheduleSaturdayMoscowSlv().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleSaturdayMoscowMolodezhnaya() {
//        scheduleMoscow = database.scheduleDao().getScheduleSaturdayMoscowMld().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleSundayMoscowAll() {
//        scheduleMoscow = database.scheduleDao().getScheduleSundayMoscowAll().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleSundayMoscowOdintsovo() {
//        scheduleMoscow = database.scheduleDao().getScheduleSundayMoscowOdn().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleSundayMoscowSlavyanka() {
//        scheduleMoscow = database.scheduleDao().getScheduleSundayMoscowSlv().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleSundayMoscowMolodezhnaya() {
//        scheduleMoscow = database.scheduleDao().getScheduleSundayMoscowMld().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleTodayDubkiAll() {
//        scheduleDubki = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
//            1 -> database.scheduleDao().getScheduleSundayDubkiAll().map { it.asDomainWithTime() }
//            7 -> database.scheduleDao().getScheduleSaturdayDubkiAll().map { it.asDomainWithTime() }
//            else -> database.scheduleDao().getScheduleWeekdayDubkiAll().map { it.asDomainWithTime() }
//        }
//    }
//
//    override suspend fun refreshScheduleTodayDubkiOdintsovo() {
//        scheduleDubki = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
//            1 -> database.scheduleDao().getScheduleSundayDubkiOdn().map { it.asDomainWithTime() }
//            7 -> database.scheduleDao().getScheduleSaturdayDubkiOdn().map { it.asDomainWithTime() }
//            else -> database.scheduleDao().getScheduleWeekdayDubkiOdn().map { it.asDomainWithTime() }
//        }
//    }
//
//    override suspend fun refreshScheduleTodayDubkiSlavyanka() {
//        scheduleDubki = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
//            1 -> database.scheduleDao().getScheduleSundayDubkiSlv().map { it.asDomainWithTime() }
//            7 -> database.scheduleDao().getScheduleSaturdayDubkiSlv().map { it.asDomainWithTime() }
//            else -> database.scheduleDao().getScheduleWeekdayDubkiSlv().map { it.asDomainWithTime() }
//        }
//    }
//
//    override suspend fun refreshScheduleTodayDubkiMolodezhnaya() {
//        scheduleDubki = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
//            1 -> database.scheduleDao().getScheduleSundayDubkiMld().map { it.asDomainWithTime() }
//            7 -> database.scheduleDao().getScheduleSaturdayDubkiMld().map { it.asDomainWithTime() }
//            else -> database.scheduleDao().getScheduleWeekdayDubkiMld().map { it.asDomainWithTime() }
//        }
//    }
//
//
//    override suspend fun refreshScheduleTomorrowDubkiAll() {
//        scheduleDubki = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
//            7 -> database.scheduleDao().getScheduleSundayDubkiAll().map { it.asDomain() }
//            6 -> database.scheduleDao().getScheduleSaturdayDubkiAll().map { it.asDomain() }
//            else -> database.scheduleDao().getScheduleWeekdayDubkiAll().map { it.asDomain() }
//        }
//    }
//
//    override suspend fun refreshScheduleTomorrowDubkiOdintsovo() {
//        scheduleDubki = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
//            7 -> database.scheduleDao().getScheduleSundayDubkiOdn().map { it.asDomain() }
//            6 -> database.scheduleDao().getScheduleSaturdayDubkiOdn().map { it.asDomain() }
//            else -> database.scheduleDao().getScheduleWeekdayDubkiOdn().map { it.asDomain() }
//        }
//    }
//
//    override suspend fun refreshScheduleTomorrowDubkiSlavyanka() {
//        scheduleDubki = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
//            7 -> database.scheduleDao().getScheduleSundayDubkiSlv().map { it.asDomain() }
//            6 -> database.scheduleDao().getScheduleSaturdayDubkiSlv().map { it.asDomain() }
//            else -> database.scheduleDao().getScheduleWeekdayDubkiSlv().map { it.asDomain() }
//        }
//    }
//
//    override suspend fun refreshScheduleTomorrowDubkiMolodezhnaya() {
//        scheduleDubki = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
//            7 -> database.scheduleDao().getScheduleSundayDubkiMld().map { it.asDomain() }
//            6 -> database.scheduleDao().getScheduleSaturdayDubkiMld().map { it.asDomain() }
//            else -> database.scheduleDao().getScheduleWeekdayDubkiMld().map { it.asDomain() }
//        }
//    }
//
//
//    override suspend fun refreshScheduleWeekdayDubkiAll() {
//        scheduleDubki = database.scheduleDao().getScheduleWeekdayDubkiAll().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleWeekdayDubkiOdintsovo() {
//        scheduleDubki = database.scheduleDao().getScheduleWeekdayDubkiOdn().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleWeekdayDubkiSlavyanka() {
//        scheduleDubki = database.scheduleDao().getScheduleWeekdayDubkiSlv().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleWeekdayDubkiMolodezhnaya() {
//        scheduleDubki = database.scheduleDao().getScheduleWeekdayDubkiMld().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleSaturdayDubkiAll() {
//        scheduleDubki = database.scheduleDao().getScheduleSaturdayDubkiAll().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleSaturdayDubkiOdintsovo() {
//        scheduleDubki = database.scheduleDao().getScheduleSaturdayDubkiOdn().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleSaturdayDubkiSlavyanka() {
//        scheduleDubki = database.scheduleDao().getScheduleSaturdayDubkiSlv().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleSaturdayDubkiMolodezhnaya() {
//        scheduleDubki = database.scheduleDao().getScheduleSaturdayDubkiMld().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleSundayDubkiAll() {
//        scheduleDubki = database.scheduleDao().getScheduleSundayDubkiAll().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleSundayDubkiOdintsovo() {
//        scheduleDubki = database.scheduleDao().getScheduleSundayDubkiOdn().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleSundayDubkiSlavyanka() {
//        scheduleDubki = database.scheduleDao().getScheduleSundayDubkiSlv().map { it.asDomain() }
//    }
//
//    override suspend fun refreshScheduleSundayDubkiMolodezhnaya() {
//        scheduleDubki = database.scheduleDao().getScheduleSundayDubkiMld().map { it.asDomain() }
//    }


    override suspend fun getNextMoscowBus(): Int {
        for (i in scheduleMoscow.indices) {
            if (scheduleMoscow[i].inTime!! >= 0) {
                return i
            }
        }
        return 0
    }

    override suspend fun getScheduleMoscow(): List<Bus> {
        Log.d("getMoscow", "$scheduleMoscow")
        return scheduleMoscow
    }


    override suspend fun getNextDubkiBus(): Int {
        for (i in scheduleDubki.indices) {
            if (scheduleDubki[i].inTime!! >= 0) {
                return i
            }
        }
        return 0
    }

    override suspend fun getScheduleDubki(): List<Bus> {
        return scheduleDubki
    }






    private suspend fun refreshDatabase() {
        suspendCoroutineWithTimeout(2000L) {
            realtimeDatabase.reference.child("schedule").child("buses").get().addOnSuccessListener { result ->
                val response : List<BusResponse> = result.children.map { snapShot ->
                    snapShot.getValue(BusResponse::class.java)!!
                }
                it.resume(response)
            }
        }?.let { l ->
            database.scheduleDao().insertSchedule(
                l.map { it.asDatabase() }
            )
        }
    }


    private suspend inline fun <T> suspendCoroutineWithTimeout(timeout: Long, crossinline block: (Continuation<T>) -> Unit ) : T? {
        var finalValue : T? = null
        withTimeoutOrNull(timeout) {
            finalValue = suspendCancellableCoroutine(block = block)
        }
        return finalValue
    }

    private fun Int.toDay(): String {
        return "sss"
    }
}