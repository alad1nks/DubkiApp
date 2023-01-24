package com.app.dubkiapp.di

import android.content.Context
import com.app.dubkiapp.repositories.ScheduleRepository
import com.app.dubkiapp.repositories.ServicesRepository
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AppModuleBinds::class,
        AppSubcomponentsModule::class,
        ViewModelBuilderModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun mainActivityComponent(): MainActivityComponent.Factory
    fun scheduleComponent(): ScheduleComponent.Factory
    fun servicesComponent(): ServicesComponent.Factory
    fun settingsComponent(): SettingsComponent.Factory

    val scheduleRepository: ScheduleRepository

    val servicesRepository: ServicesRepository

}


@Module(
    subcomponents = [
        MainActivityComponent::class,
        ScheduleComponent::class,
        ServicesComponent::class,
        SettingsComponent::class
    ]
)
object AppSubcomponentsModule
