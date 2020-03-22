package com.pshetye.usgs.di.components

import com.pshetye.apimodeler.di.components.ApplicationComponent
import com.pshetye.usgs.di.modules.NetworkModule
import com.pshetye.usgs.di.scopes.USGS
import dagger.Component

@[USGS Component(
    dependencies = [ApplicationComponent::class],
    modules = [NetworkModule::class]
)]
interface UsgsComponent {
    @Component.Factory
    interface Factory {
        fun create(applicationComponent: ApplicationComponent): UsgsComponent
    }
}