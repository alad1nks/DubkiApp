package com.app.dubkiapp.di

import com.app.dubkiapp.ui.view.ScheduleFragment
import com.app.dubkiapp.ui.view.ScheduleMoscowFragment
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
    fun inject(fragment: ScheduleMoscowFragment)
}