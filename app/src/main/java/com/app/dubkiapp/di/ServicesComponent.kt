package com.app.dubkiapp.di

import com.app.dubkiapp.ui.view.ServicesFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [
        ServicesModule::class
    ]
)
interface ServicesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ServicesComponent
    }

    fun inject(fragment: ServicesFragment)

}