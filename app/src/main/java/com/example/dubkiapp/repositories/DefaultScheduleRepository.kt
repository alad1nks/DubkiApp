package com.example.dubkiapp.repositories

import android.content.ContentValues.TAG
import android.util.Log
import com.example.dubkiapp.database.ScheduleDatabase
import com.example.dubkiapp.database.domain.asDomain
import com.example.dubkiapp.domain.Bus
import com.example.dubkiapp.network.BusResponse
import com.example.dubkiapp.network.asDatabaseDubkiSaturday
import com.example.dubkiapp.network.asDatabaseDubkiSunday
import com.example.dubkiapp.network.asDatabaseDubkiWeekday
import com.example.dubkiapp.network.asDatabaseMoscowSaturday
import com.example.dubkiapp.network.asDatabaseMoscowSunday
import com.example.dubkiapp.network.asDatabaseMoscowWeekday
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

            refreshMoscowWeekdayDatabase()
            Log.d(TAG, "weekday")
            refreshMoscowSaturdayDatabase()
            Log.d(TAG, "saturday")
            refreshMoscowSundayDatabase()
            Log.d(TAG, "sunday")

            refreshDubkiWeekdayDatabase()
            Log.d(TAG, "Dweekday")
            refreshDubkiSaturdayDatabase()
            Log.d(TAG, "Dsaturday")
            refreshDubkiSundayDatabase()
            Log.d(TAG, "Dsunday")
        } else {
        }
    }

    override suspend fun refreshScheduleMoscowDatabase() {
        scheduleMoscow = when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            1 -> database.scheduleDao().getScheduleMoscowSunday().map { it.asDomain(Calendar.getInstance().timeInMillis) }
            7 -> database.scheduleDao().getScheduleMoscowSaturday().map { it.asDomain(Calendar.getInstance().timeInMillis) }
            else -> database.scheduleDao().getScheduleMoscowWeekday().map { it.asDomain(Calendar.getInstance().timeInMillis) }
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

    override suspend fun refreshScheduleDubkiDatabase() {
        scheduleDubki = when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            1 -> database.scheduleDao().getScheduleDubkiSunday().map { it.asDomain(Calendar.getInstance().timeInMillis) }
            7 -> database.scheduleDao().getScheduleDubkiSaturday().map { it.asDomain(Calendar.getInstance().timeInMillis) }
            else -> database.scheduleDao().getScheduleDubkiWeekday().map { it.asDomain(Calendar.getInstance().timeInMillis) }
        }
        for (i in scheduleDubki.indices) {
            if (scheduleDubki[i].inTime!! >= 0) {
                nextDubkiBus = i
                return
            }
        }
    }

    override suspend fun getNextDubkiBus(): Int {
        return nextDubkiBus
    }

    override suspend fun getScheduleDubki(): List<Bus> {
        return scheduleDubki
    }

    private suspend fun refreshMoscowWeekdayDatabase() {
        suspendCoroutineWithTimeout(2000L) {
            realtimeDatabase.reference.child("schedule").child("city").child("weekday").get().addOnSuccessListener { result ->
                val response : List<BusResponse> = result.children.map { snapShot ->
                    snapShot.getValue(BusResponse::class.java)!!
                }
                it.resume(response)
            }
        }?.let { l ->
            database.scheduleDao().insertScheduleMoscowWeekday(
                l.map { it.asDatabaseMoscowWeekday() }
            )
        }
    }

    private suspend fun refreshMoscowSaturdayDatabase() {
        suspendCoroutineWithTimeout(2000L) {
            realtimeDatabase.reference.child("schedule").child("city").child("saturday").get().addOnSuccessListener { result ->
                val response : List<BusResponse> = result.children.map { snapShot ->
                    snapShot.getValue(BusResponse::class.java)!!
                }
                it.resume(response)
            }
        }?.let { l ->
            database.scheduleDao().insertScheduleMoscowSaturday(
                l.map { it.asDatabaseMoscowSaturday() }
            )
        }
    }

    private suspend fun refreshMoscowSundayDatabase() {
        suspendCoroutineWithTimeout(2000L) {
            realtimeDatabase.reference.child("schedule").child("city").child("sunday").get().addOnSuccessListener { result ->
                val response : List<BusResponse> = result.children.map { snapShot ->
                    snapShot.getValue(BusResponse::class.java)!!
                }
                it.resume(response)
            }
        }?.let { l ->
            database.scheduleDao().insertScheduleMoscowSunday(
                l.map { it.asDatabaseMoscowSunday() }
            )
        }
    }

    private suspend fun refreshDubkiWeekdayDatabase() {
        suspendCoroutineWithTimeout(2000L) {
            realtimeDatabase.reference.child("schedule").child("dubki").child("weekday").get().addOnSuccessListener { result ->
                val response : List<BusResponse> = result.children.map { snapShot ->
                    snapShot.getValue(BusResponse::class.java)!!
                }
                it.resume(response)
            }
        }?.let { l ->
            database.scheduleDao().insertScheduleDubkiWeekday(
                l.map { it.asDatabaseDubkiWeekday() }
            )
        }
    }

    private suspend fun refreshDubkiSaturdayDatabase() {
        suspendCoroutineWithTimeout(2000L) {
            realtimeDatabase.reference.child("schedule").child("dubki").child("saturday").get().addOnSuccessListener { result ->
                val response : List<BusResponse> = result.children.map { snapShot ->
                    snapShot.getValue(BusResponse::class.java)!!
                }
                it.resume(response)
            }
        }?.let { l ->
            database.scheduleDao().insertScheduleDubkiSaturday(
                l.map { it.asDatabaseDubkiSaturday() }
            )
        }
    }

    private suspend fun refreshDubkiSundayDatabase() {
        suspendCoroutineWithTimeout(2000L) {
            realtimeDatabase.reference.child("schedule").child("dubki").child("sunday").get().addOnSuccessListener { result ->
                val response : List<BusResponse> = result.children.map { snapShot ->
                    snapShot.getValue(BusResponse::class.java)!!
                }
                it.resume(response)
            }
        }?.let { l ->
            database.scheduleDao().insertScheduleDubkiSunday(
                l.map { it.asDatabaseDubkiSunday() }
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