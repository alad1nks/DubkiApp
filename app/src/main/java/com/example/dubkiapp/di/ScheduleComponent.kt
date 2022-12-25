package com.example.dubkiapp.di


import com.example.dubkiapp.ui.schedule.ScheduleFragment
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