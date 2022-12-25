package com.example.dubkiapp.di

import androidx.lifecycle.ViewModel
import com.example.dubkiapp.ui.schedule.ScheduleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ScheduleModule {

    @Binds
    @IntoMap
    @ViewModelKey(ScheduleViewModel::class)
    abstract fun bindViewModel(viewmodel: ScheduleViewModel): ViewModel

}