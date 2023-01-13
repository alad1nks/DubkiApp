package com.app.dubkiapp.di

import android.content.Context
import com.app.dubkiapp.repositories.ScheduleRepository
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
    fun settingsComponent(): SettingsComponent.Factory

    val scheduleRepository: ScheduleRepository

}


@Module(
    subcomponents = [
        MainActivityComponent::class,
        ScheduleComponent::class,
        SettingsComponent::class
    ]
)
object AppSubcomponentsModule
