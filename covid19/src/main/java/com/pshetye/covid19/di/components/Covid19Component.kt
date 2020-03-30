package com.pshetye.covid19.di.components

import com.pshetye.apimodeler.di.components.ActivityComponent
import com.pshetye.covid19.di.modules.NetworkModule
import com.pshetye.covid19.di.scopes.Covid19
import com.pshetye.covid19.ui.countries.fragments.CountriesFragment
import dagger.Component

@[Covid19 Component(
    dependencies = [ActivityComponent::class],
    modules = [NetworkModule::class]
)]
interface Covid19Component {
    @Component.Factory
    interface Factory {
        fun create(
            activityComponent: ActivityComponent
        ): Covid19Component
    }

    fun inject(countries: CountriesFragment)
}