package com.example.dubkiapp.di

import com.example.dubkiapp.preferences.AppPreference
import com.example.dubkiapp.preferences.AppPreferenceImpl
import com.example.dubkiapp.repositories.DefaultScheduleRepository
import com.example.dubkiapp.repositories.ScheduleRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModuleBinds {

    @Singleton
    @Binds
    abstract fun bindScheduleRepository(repo: DefaultScheduleRepository) : ScheduleRepository

    @Binds
    abstract fun bindSharedPreferences(appPreferenceImpl: AppPreferenceImpl): AppPreference

}