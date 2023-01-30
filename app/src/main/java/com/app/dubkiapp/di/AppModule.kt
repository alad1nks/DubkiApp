package com.app.dubkiapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.app.dubkiapp.database.ScheduleDatabase
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {
    private lateinit var INSTANCESCHEDULE: ScheduleDatabase

    @Provides
    @Singleton
    fun provideSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences("dagger-pref", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideScheduleDatabase(context: Context): ScheduleDatabase {
        synchronized(ScheduleDatabase::class.java) {
            if (!::INSTANCESCHEDULE.isInitialized) {
                INSTANCESCHEDULE = Room.databaseBuilder(context.applicationContext,
                    ScheduleDatabase::class.java,
                    "schedule_database")
                    .build()
            }
        }
        return INSTANCESCHEDULE
    }


    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase = FirebaseDatabase.getInstance()

}