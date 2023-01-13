package com.app.dubkiapp.di

import com.app.dubkiapp.ui.settings.SettingsFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [
        SettingsModule::class
    ]
)
interface SettingsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SettingsComponent
    }

    fun inject(fragment: SettingsFragment)

}