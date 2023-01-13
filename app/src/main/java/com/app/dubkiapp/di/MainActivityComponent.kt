package com.app.dubkiapp.di

import com.app.dubkiapp.MainActivity
import dagger.Subcomponent


@Subcomponent
interface MainActivityComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivityComponent
    }

    fun inject(activity: MainActivity)


}
