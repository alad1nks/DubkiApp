package com.example.dubkiapp.di

import com.example.dubkiapp.MainActivity
import dagger.Module
import dagger.Subcomponent
import javax.inject.Singleton


@Subcomponent
interface MainActivityComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivityComponent
    }

    fun inject(activity: MainActivity)


}
