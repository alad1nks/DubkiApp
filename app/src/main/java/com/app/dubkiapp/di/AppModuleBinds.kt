package com.app.dubkiapp.di

import com.app.dubkiapp.preferences.AppPreference
import com.app.dubkiapp.preferences.AppPreferenceImpl
import com.app.dubkiapp.repositories.DefaultScheduleRepository
import com.app.dubkiapp.repositories.ScheduleRepository
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