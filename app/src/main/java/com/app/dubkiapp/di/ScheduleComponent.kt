package com.app.dubkiapp.di

import com.app.dubkiapp.ui.schedule.ScheduleFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [
        ScheduleModule::class
    ]
)
interface ScheduleComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): ScheduleComponent
    }

    fun inject(fragment: ScheduleFragment)
}