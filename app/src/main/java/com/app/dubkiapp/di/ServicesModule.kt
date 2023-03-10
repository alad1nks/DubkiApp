package com.app.dubkiapp.di

import androidx.lifecycle.ViewModel
import com.app.dubkiapp.ui.stateholders.ServicesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ServicesModule {

    @Binds
    @IntoMap
    @ViewModelKey(ServicesViewModel::class)
    abstract fun bindViewModel(viewmodel: ServicesViewModel): ViewModel

}