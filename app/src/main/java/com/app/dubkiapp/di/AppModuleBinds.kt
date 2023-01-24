package com.app.dubkiapp.di

import com.app.dubkiapp.preferences.AppPreference
import com.app.dubkiapp.preferences.AppPreferenceImpl
import com.app.dubkiapp.repositories.DefaultScheduleRepository
import com.app.dubkiapp.repositories.DefaultServicesRepository
import com.app.dubkiapp.repositories.ScheduleRepository
import com.app.dubkiapp.repositories.ServicesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModuleBinds {

    @Singleton
    @Binds
    abstract fun bindScheduleRepository(repo: DefaultScheduleRepository) : ScheduleRepository

    @Singleton
    @Binds
    abstract fun bindServicesRepository(repo: DefaultServicesRepository) : ServicesRepository

    @Binds
    abstract fun bindSharedPreferences(appPreferenceImpl: AppPreferenceImpl): AppPreference

}