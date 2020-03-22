package com.pshetye.usgs.di.components

import com.pshetye.apimodeler.di.components.ActivityComponent
import com.pshetye.usgs.di.modules.NetworkModule
import com.pshetye.usgs.di.scopes.USGS
import com.pshetye.usgs.ui.fragments.UsgsEarthquakes
import dagger.Component

@[USGS Component(
    dependencies = [ActivityComponent::class],
    modules = [NetworkModule::class]
)]
interface UsgsComponent {
    @Component.Factory
    interface Factory {
        fun create(applicationComponent: ActivityComponent): UsgsComponent
    }

    fun inject(usgsEarthquakes: UsgsEarthquakes)
}