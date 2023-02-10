package com.app.dubkiapp.di

import androidx.lifecycle.ViewModel
import com.app.dubkiapp.ui.stateholders.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SettingsModule {

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindViewModel(viewmodel: SettingsViewModel): ViewModel

}