package com.example.dubkiapp

import android.app.Application
import com.example.dubkiapp.di.AppComponent
import com.example.dubkiapp.di.DaggerAppComponent

open class DubkiApp: Application() {
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}