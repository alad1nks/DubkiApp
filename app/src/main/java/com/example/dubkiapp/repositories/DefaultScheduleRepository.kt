package com.example.dubkiapp.repositories

import android.content.ContentValues.TAG
import android.util.Log
import com.example.dubkiapp.database.ScheduleDatabase
import com.example.dubkiapp.database.domain.asDomain
import com.example.dubkiapp.domain.Bus
import com.example.dubkiapp.network.BusResponse
import com.example.dubkiapp.network.asDatabase
import com.example.dubkiapp.preferences.AppPreference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeoutOrNull
import java.util.Calendar
import javax.inject.Inject
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

class DefaultScheduleRepository @Inject constructor(
    private val realtimeDatabase: FirebaseDatabase,
    private val database: ScheduleDatabase,
    private val appPreference: AppPreference
): ScheduleRepository {
    private var scheduleMoscow: List<Bus> = listOf()
    private var nextMoscowBus: Int = 0
    private var scheduleDubki: List<Bus> = listOf()
    private var nextDubkiBus: Int = 0
    private var revision: Int? = null

    override suspend fun refreshScheduleFirebase() {
        revision = suspendCoroutineWithTimeout(2000L) {
            realtimeDatabase.reference.child("schedule").child("revision").get().addOnSuccessListener { result ->
                val response = result.getValue(Long::class.java)
                it.resume(response!!.toInt())
            }
        }

        if (revision == null) {
        } else if (revision!! != appPreference.getRevision()) {
            appPreference.setRevision(revision!!)
            refreshDatabase()

        } else {
        }
    }

    override suspend fun refreshScheduleMoscowDatabase(day: String, station: String) {
        when(day) {
            "cur" -> {
                when(station) {
                    "all" -> {
                        scheduleMoscow = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
                            1 -> database.scheduleDao().getScheduleSundayMoscowAll().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                            7 -> database.scheduleDao().getScheduleSaturdayMoscowAll().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                            else -> database.scheduleDao().getScheduleWeekdayMoscowAll().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                        }
                    }
                    "odn" -> {
                        scheduleMoscow = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
                            1 -> database.scheduleDao().getScheduleSundayMoscowOdn().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                            7 -> database.scheduleDao().getScheduleSaturdayMoscowOdn().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                            else -> database.scheduleDao().getScheduleWeekdayMoscowOdn().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                        }
                    }
                    "slv" -> {
                        scheduleMoscow = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
                            1 -> database.scheduleDao().getScheduleSundayMoscowSlv().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                            7 -> database.scheduleDao().getScheduleSaturdayMoscowSlv().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                            else -> database.scheduleDao().getScheduleWeekdayMoscowSlv().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                        }
                    }
                    else -> {
                        scheduleMoscow = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
                            1 -> database.scheduleDao().getScheduleSundayMoscowMld().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                            7 -> database.scheduleDao().getScheduleSaturdayMoscowMld().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                            else -> database.scheduleDao().getScheduleWeekdayMoscowMld().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                        }
                    }
                }
            }
            "wkd" -> {
                scheduleMoscow = when(station) {
                    "all" -> {
                        database.scheduleDao().getScheduleWeekdayMoscowAll().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }

                    "odn" -> {
                        database.scheduleDao().getScheduleWeekdayMoscowOdn().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }

                    "slv" -> {
                        database.scheduleDao().getScheduleWeekdayMoscowSlv().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }

                    else -> {
                        database.scheduleDao().getScheduleWeekdayMoscowMld().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }
                }
            }
            "std" -> {
                scheduleMoscow = when(station) {
                    "all" -> {
                        database.scheduleDao().getScheduleSaturdayMoscowAll().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }

                    "odn" -> {
                        database.scheduleDao().getScheduleSaturdayMoscowOdn().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }

                    "slv" -> {
                        database.scheduleDao().getScheduleSaturdayMoscowSlv().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }

                    else -> {
                        database.scheduleDao().getScheduleSaturdayMoscowMld().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }
                }
            }
            "snd" -> {
                scheduleMoscow = when(station) {
                    "all" -> {
                        database.scheduleDao().getScheduleSundayMoscowAll().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }

                    "odn" -> {
                        database.scheduleDao().getScheduleSundayMoscowOdn().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }

                    "slv" -> {
                        database.scheduleDao().getScheduleSundayMoscowSlv().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }

                    else -> {
                        database.scheduleDao().getScheduleSundayMoscowMld().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }
                }
            }
        }
        for (i in scheduleMoscow.indices) {
            if (scheduleMoscow[i].inTime!! >= 0) {
                nextMoscowBus = i
                return
            }
        }
    }

    override suspend fun getNextMoscowBus(): Int {
        Log.d(TAG, "getNextMoscowBus${nextMoscowBus}")
        return nextMoscowBus
    }

    override suspend fun getScheduleMoscow(): List<Bus> {
        Log.d(TAG, "getScheduleMoscow${nextDubkiBus}")
        return scheduleMoscow
    }


    override suspend fun refreshScheduleDubkiDatabase(day: String, station: String) {
        when(day) {
            "cur" -> {
                when(station) {
                    "all" -> {
                        scheduleDubki = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
                            1 -> database.scheduleDao().getScheduleSundayDubkiAll().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                            7 -> database.scheduleDao().getScheduleSaturdayDubkiAll().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                            else -> database.scheduleDao().getScheduleWeekdayDubkiAll().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                        }
                    }
                    "odn" -> {
                        scheduleDubki = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
                            1 -> database.scheduleDao().getScheduleSundayDubkiOdn().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                            7 -> database.scheduleDao().getScheduleSaturdayDubkiOdn().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                            else -> database.scheduleDao().getScheduleWeekdayDubkiOdn().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                        }
                    }
                    "slv" -> {
                        scheduleDubki = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
                            1 -> database.scheduleDao().getScheduleSundayDubkiSlv().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                            7 -> database.scheduleDao().getScheduleSaturdayDubkiSlv().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                            else -> database.scheduleDao().getScheduleWeekdayDubkiSlv().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                        }
                    }
                    else -> {
                        scheduleDubki = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
                            1 -> database.scheduleDao().getScheduleSundayDubkiMld().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                            7 -> database.scheduleDao().getScheduleSaturdayDubkiMld().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                            else -> database.scheduleDao().getScheduleWeekdayDubkiMld().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                        }
                    }
                }
            }
            "wkd" -> {
                scheduleDubki = when(station) {
                    "all" -> {
                        database.scheduleDao().getScheduleWeekdayDubkiAll().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }

                    "odn" -> {
                        database.scheduleDao().getScheduleWeekdayDubkiOdn().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }

                    "slv" -> {
                        database.scheduleDao().getScheduleWeekdayDubkiSlv().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }

                    else -> {
                        database.scheduleDao().getScheduleWeekdayDubkiMld().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }
                }
            }
            "std" -> {
                scheduleDubki = when(station) {
                    "all" -> {
                        database.scheduleDao().getScheduleSaturdayDubkiAll().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }

                    "odn" -> {
                        database.scheduleDao().getScheduleSaturdayDubkiOdn().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }

                    "slv" -> {
                        database.scheduleDao().getScheduleSaturdayDubkiSlv().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }

                    else -> {
                        database.scheduleDao().getScheduleSaturdayDubkiMld().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }
                }
            }
            "snd" -> {
                scheduleDubki = when(station) {
                    "all" -> {
                        database.scheduleDao().getScheduleSundayDubkiAll().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }

                    "odn" -> {
                        database.scheduleDao().getScheduleSundayDubkiOdn().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }

                    "slv" -> {
                        database.scheduleDao().getScheduleSundayDubkiSlv().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }

                    else -> {
                        database.scheduleDao().getScheduleSundayDubkiMld().map { it.asDomain(Calendar.getInstance().timeInMillis) }
                    }
                }
            }
        }
        for (i in scheduleDubki.indices) {
            if (scheduleDubki[i].inTime!! >= 0) {
                nextDubkiBus = i
                return
            }
        }
    }

    override suspend fun getNextDubkiBus(): Int {
        Log.d(TAG, "getNextMoscowBus${nextMoscowBus}")
        return nextDubkiBus
    }

    override suspend fun getScheduleDubki(): List<Bus> {
        Log.d(TAG, "getScheduleMoscow${nextDubkiBus}")
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
}