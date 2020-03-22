package com.pshetye.apimodeler.app

import android.app.Application
import com.pshetye.apimodeler.di.components.ApplicationComponent
import com.pshetye.apimodeler.di.components.DaggerApplicationComponent

class ApiModelerApplication : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initializerApplicationComponent()
    }

    private fun initializerApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.factory().create()
    }
}