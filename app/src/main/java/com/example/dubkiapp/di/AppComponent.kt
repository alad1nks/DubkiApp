package com.example.dubkiapp.di

import android.content.Context
import com.example.dubkiapp.repositories.ScheduleRepository
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

    val scheduleRepository: ScheduleRepository

}


@Module(
    subcomponents = [
        MainActivityComponent::class,
        ScheduleComponent::class
    ]
)
object AppSubcomponentsModule
