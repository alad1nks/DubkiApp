package com.app.dubkiapp.di

import com.app.dubkiapp.preferences.AppPreference
import com.app.dubkiapp.preferences.AppPreferenceImpl
import com.app.dubkiapp.data.repositories.ScheduleRepositoryImpl
import com.app.dubkiapp.data.repositories.ServicesRepositoryImpl
import com.app.dubkiapp.domain.repositories.ScheduleRepository
import com.app.dubkiapp.domain.repositories.ServicesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModuleBinds {

    @Singleton
    @Binds
    abstract fun bindScheduleRepository(repo: ScheduleRepositoryImpl) : ScheduleRepository

    @Singleton
    @Binds
    abstract fun bindServicesRepository(repo: ServicesRepositoryImpl) : ServicesRepository

    @Binds
    abstract fun bindSharedPreferences(appPreferenceImpl: AppPreferenceImpl): AppPreference

}