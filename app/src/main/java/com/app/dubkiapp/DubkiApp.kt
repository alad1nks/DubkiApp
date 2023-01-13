package com.app.dubkiapp

import android.app.Application
import com.app.dubkiapp.di.AppComponent
import com.app.dubkiapp.di.DaggerAppComponent

open class DubkiApp: Application() {
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}